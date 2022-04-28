package com.esports.manager.userManagement.logic;

import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.util.DataSourceCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserManagementTest {

    private Logger log = LogManager.getLogger(UserManagementTest.class);

    /**
     * Unfortunately resource injection using the @{@link jakarta.annotation.Resource} annotation is not possible
     * without a container (=application server). We don't have a container when unit-testing, so we need to create the DataSource ourselves
     * and inject it manually.
     * @author Daniel Mehlber
     */
    @BeforeAll
    public static void injectDataSource() throws SQLException {
        // create datasource and inject it into the QueryHandler
        DataSource dataSource = DataSourceCreator.createNewDataSource();
        QueryHandler.setDataSource(dataSource);
    }


    @Test
    public void fetchByUserName_userExists() throws NoSuchUserException, InternalErrorException {
        // TODO: add user with username automatically

        // User user = UserManagement.fetchUserByUsername("username");
        // Assertions.assertEquals(user.getUsername(), "username");
    }

}
