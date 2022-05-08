package com.esports.manager.userManagement.logic;

import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.util.DataSourceCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserManagementTest {


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

    @Test
    public void createNewUser() {
        // create user with method
        // fetch user by this username in order to assert that it was created
    }

    @Test
    public void loginUser() {
        // create user
        // login user using method in UserManagement
        // check if user is logged in
    }

    @Test
    public void registerUser() {
        // register user using method in UserManagement
        // fetch user with this username in order to assert that he was registered
    }

    @Test
    public void isUserLoggedIn() {
        // create user
        // login user
        // assert that user is logged in using the method in UserRegistration
    }
}
