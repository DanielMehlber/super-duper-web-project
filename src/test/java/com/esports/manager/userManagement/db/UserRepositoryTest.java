package com.esports.manager.userManagement.db;

import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoImageFoundException;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.WrongCredentialsException;
import com.esports.manager.userManagement.logic.UserManagement;
import com.esports.manager.util.DataSourceCreator;
import com.esports.manager.util.MockHttpSession;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryTest {

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
        QueryHandler.setGlobalDataSource(dataSource);
    }

    @BeforeEach
    public void prepare() throws Exception {
        // clear table of users
        PreparedStatement statement = QueryHandler.loadStatement("/sql/user-management/delete-all-users.sql");
        statement.executeUpdate();
    }

    @Test
    public void uploadAndLoadProfilePicture() throws InternalErrorException, NoSuchUserException, WrongCredentialsException, NoImageFoundException {
        // just some random bytes
        byte[] picture = new byte[] {12, 13, 123, 32, 26, 45, 52, 45};
        User user = new User("username", "username@username.com", UserManagement.hashPassword("password"));
        HttpSession session = new MockHttpSession();
        // create user and login
        UserRepository.createNewUser(user);
        UserManagement.performLogin("username", "password", session);

        // upload image data
        UserRepository.setProfileImage(picture, user);

        // assert that correct data has been uploaded
        byte[] receivedImage = UserRepository.loadProfileImage(user.getUsername());
        Assertions.assertArrayEquals(receivedImage, picture);
    }

    @Test
    public void uploadAndLoadBackgroundPicture() throws InternalErrorException, NoSuchUserException, WrongCredentialsException, NoImageFoundException {
        // just some random bytes
        byte[] picture = new byte[] {12, 13, 123, 32, 26, 45, 52, 45};
        User user = new User("username", "username@username.com", UserManagement.hashPassword("password"));
        HttpSession session = new MockHttpSession();
        // create user and login
        UserRepository.createNewUser(user);
        UserManagement.performLogin("username", "password", session);

        // upload image data
        UserRepository.setBackgroundImage(picture, user);

        // assert that correct data has been uploaded
        byte[] receivedImage = UserRepository.loadBackgroundImage(user.getUsername());
        Assertions.assertArrayEquals(receivedImage, picture);
    }

}
