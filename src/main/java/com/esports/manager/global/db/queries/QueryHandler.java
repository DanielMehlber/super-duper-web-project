package com.esports.manager.global.db.queries;

import com.esports.manager.global.exceptions.InternalErrorException;
import jakarta.annotation.Resource;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Queries must be loaded from file and converted to statements. This class handles the query loading centrally.
 */
@WebServlet(loadOnStartup=1)
public class QueryHandler extends HttpServlet {

    private static final Logger log = LogManager.getLogger(QueryHandler.class);

    /**
     * Caches already loaded prepared statement sql sources.
     */
    private static final Map<String, String> cache = new HashMap<>();

    private static DataSource dataSource;

    @Resource(lookup = "java:jboss/datasources/eSportsDS")
    public void setDataSource(final DataSource _dataSource) {
        dataSource = _dataSource;
    }

    public static void setGlobalDataSource(final DataSource _dataSource) {
        dataSource = _dataSource;
    }

    /**
     * Load the resource file as a prepared statement.
     * @param resourcePath path to the .sql resource (located in the classpath)
     * @return prepared statement containing the sql source
     * @throws IOException cannot load or find file on resourcePath
     * @throws InternalErrorException some unexpected and fatal error has occurred
     * @author Daniel Mehlber
     * @see QueryHandler#loadSource(String) 
     */
    public static PreparedStatement loadStatement(final String resourcePath) throws IOException, InternalErrorException {
        log.debug(String.format("loading prepared statement '%s'", resourcePath));
        try {
            String source;
            // first check if statement has already been loaded and resides in cache
            if (cache.containsKey(resourcePath)) {
                // source is in cache, load from there
                source = cache.get(resourcePath);
            } else {
                // source is not in cache, load from file
                source = loadSource(resourcePath);
            }


            return dataSource.getConnection().prepareStatement(source);

        } catch (final SQLException e) {
            log.error("cannot load and prepare statement due to an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot load and prepare statement", e);
        }
    }

    /**
     * Loads source of .sql file as string
     * @param resourcePath path to the .sql resource (located in the classpath)
     * @return source of file as String
     * @throws IOException cannot load or find file in resourcePath
     * @author Daniel Mehlber
     */
    private static String loadSource(final String resourcePath) throws IOException {
        /*
         * load file contents as string using way too many objects and instantiations...
         * Wrap all inside a try-with-resource block: if something fails, all objects/files will be closed
         */

        InputStream inputStream = QueryHandler.class.getResourceAsStream(resourcePath);
        if(inputStream == null)
            throw new IOException(String.format("cannot open or read file '%s'", resourcePath));

        StringBuilder sourceBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            // read file line by line and append each to string builder
            while ((line = reader.readLine()) != null) {
                sourceBuilder.append(line).append("\n");
            }
        }
        return sourceBuilder.toString();
    }
}
