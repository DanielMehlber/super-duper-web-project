package com.esports.manager.userManagement.logic;

import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.UsernameAlreadyTakenException;
import com.esports.manager.util.DataSourceCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
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

    @BeforeEach
    public void prepare() throws Exception {
        // clear table of users
        PreparedStatement statement = QueryHandler.loadStatement("/sql/user-management/delete-all-users.sql");
        statement.executeUpdate();
    }


    @Test
    public void registerUser_usernameAlreadyTaken() throws InternalErrorException, UsernameAlreadyTakenException {
        // -- arrange --
        // create user in database
        User createdUser = new User("username", "username@username.com", UserManagement.hashPassword("password123"));
        UserRepository.createNewUser(createdUser);

        // -- act and assert --
        // try to register user with same username. Expect throw of UsernameAlreadyTakenException
        Assertions.assertThrows(UsernameAlreadyTakenException.class, () -> UserManagement.registerUser(createdUser.getUsername(), "password123", createdUser.getEmail()));
    }

    @Test
    public void registerUser_happyPath() throws InternalErrorException, UsernameAlreadyTakenException, InvalidInputException, NoSuchUserException {
        // -- arrange --
        String username = "username";
        String password = "password123";
        String email = "username@username.com";

        // -- act --
        // register user
        UserManagement.registerUser(username, password, email);

        // -- assert --
        // check if user is in database
        User fetchedUser = UserManagement.fetchUserByUsername(username);
        Assertions.assertNotNull(fetchedUser);
        // check that values that have been stored in database are correct
        Assertions.assertEquals(username, fetchedUser.getUsername());
        Assertions.assertEquals(email, fetchedUser.getEmail());
        Assertions.assertEquals(UserManagement.hashPassword(password), fetchedUser.getPasswordHash());
    }
}
