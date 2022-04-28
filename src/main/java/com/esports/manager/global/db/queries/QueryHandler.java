package com.esports.manager.global.db.queries;

import com.esports.manager.global.exceptions.InternalErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Queries must be loaded from file and converted to statements. This class handles the query loading centrally.
 */
public class QueryHandler {

    private static Logger log = LogManager.getLogger(QueryHandler.class);

    /**
     * Caches already loaded prepared statements. Why? Look at the documentation of {@link PreparedStatement}:
     * <p><i>An object that represents a precompiled SQL statement. A SQL statement is precompiled and stored in a
     * PreparedStatement object. This object can then be used to <b>efficiently execute this statement multiple times</b></i>
     * (=re-usability).
     */
    private static Map<String, PreparedStatement> cache = new HashMap<>();

    /**
     * This datasource will be injected by the {@link com.esports.manager.userManagement.servlets.ApplicationInitServlet}
     * at application startup. Unfortunately the container only injects {@link DataSource} in registered servlets.
     */
    private static DataSource dataSource;

    public static void setDataSource(final DataSource _dataSource) {
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
            // first check if statement has already been loaded and resides in cache
            if (cache.containsKey(resourcePath)) {
                PreparedStatement cached = cache.get(resourcePath);
                // check if prepared statement can still be used (if it hasn't been closed)
                if (!cached.isClosed()) return cached;
            }

            // either the statement has not been loaded yet or is invalid/closed
            // we have to load and create it again
            String source = loadSource(resourcePath);
            PreparedStatement newStatement = dataSource.getConnection().prepareStatement(source);

            // cache prepared statement
            cache.put(resourcePath, newStatement);
            return newStatement;

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
