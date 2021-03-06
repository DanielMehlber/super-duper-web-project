package com.esports.manager.userManagement.logic;

import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.*;
import com.esports.manager.util.DataSourceCreator;
import com.esports.manager.util.MockHttpSession;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

public class UserManagementTest {


    /**
     * Unfortunately resource injection using the @{@link jakarta.annotation.Resource} annotation is not possible
     * without a container (=application server). We don't have a container when unit-testing, so we need to create the DataSource ourselves
     * and inject it manually.
     * @author Daniel Mehlber
     */
    @BeforeAll
    public static void injectDataSource() {
        // create datasource and inject it into the QueryHandler
        DataSource dataSource = DataSourceCreator.createNewDataSource();
        QueryHandler.setGlobalDataSource(dataSource);
    }

    @BeforeEach
    public void prepare() throws Exception {
        // clear table of users
        PreparedStatement statement = QueryHandler.loadStatement("/sql/user-management/delete-all-users.sql");
        statement.executeUpdate();
        statement.close();
    }


    @Test
    public void registerUser_usernameAlreadyTaken() throws InternalErrorException {
        // -- arrange --
        // create user in database
        User createdUser = new User("username", "username@username.com", UserManagement.hashPassword("password123"), false);
        UserRepository.createNewUser(createdUser);

        // -- act and assert --
        // try to register user with same username. Expect throw of UsernameAlreadyTakenException
        Assertions.assertThrows(UsernameAlreadyTakenException.class, () -> UserManagement.registerUser(createdUser.getUsername(), "password123", createdUser.getEmail(), false));
    }

    @Test
    public void registerUser_happyPath() throws InternalErrorException, UsernameAlreadyTakenException, InvalidInputException, NoSuchUserException {
        // -- arrange --
        String username = "username";
        String password = "password123";
        String email = "username@username.com";
        boolean isAdmin = false;

        // -- act --
        // register user
        UserManagement.registerUser(username, password, email, false);

        // -- assert --
        // check if user is in database
        User fetchedUser = UserManagement.fetchUserByUsername(username);
        Assertions.assertNotNull(fetchedUser);
        // check that values that have been stored in database are correct
        Assertions.assertEquals(username, fetchedUser.getUsername());
        Assertions.assertEquals(email, fetchedUser.getEmail());
        Assertions.assertEquals(UserManagement.hashPassword(password), fetchedUser.getPasswordHash());
    }

    // TODO: test registration with invalid username (e.g. funny characters or too long/short)
    // TODO: test registration with invalid email
    // TODO: test registration with invalid password

    @Test
    public void performLogin_happyPath() throws InternalErrorException, NoSuchUserException, WrongCredentialsException, UnauthorizedException {
        HttpSession session = new MockHttpSession();

        // check that no user is logged in
        Assertions.assertThrows(UnauthorizedException.class, () -> UserManagement.getAuthorizedUser(session));

        // create user for login
        String username = "username";
        String password = "password123";
        String email = "username@username.com";
        Boolean isAdmin = false;
        User createdUser = new User(username, email, UserManagement.hashPassword(password), false);
        UserRepository.createNewUser(createdUser);

        // -- act --
        UserManagement.performLogin(username, password, session);

        // -- assert --
        User loggedIn = UserManagement.getAuthorizedUser(session);
        Assertions.assertEquals(loggedIn.getUsername(), createdUser.getUsername());
    }
    // TODO: test login with username that does not exist
    // TODO: test login with wrong password
}
