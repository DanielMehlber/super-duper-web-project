package com.esports.manager.userManagement.db;

import com.esports.manager.global.db.mapping.ResultSetProcessor;

import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.UsernameAlreadyTakenException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Database interactions with user entities.
 */
public class UserRepository {

    private static final Logger log = LogManager.getLogger(UserRepository.class);

    /**
     * fetch user with username from database
     * @param username of user
     * @return user instance fetched from database
     * @throws InternalErrorException an unexpected internal error occurred
     * @throws NoSuchUserException no such user with passed username
     * @author Daniel Mehlber
     */
    public static User getByUsername(final String username) throws InternalErrorException, NoSuchUserException {
        log.debug("fetching user entity by username from database...");

        // execute query and attempt to fetch user from database
        ResultSet resultSet;
        try{
            PreparedStatement statement = QueryHandler.loadStatement("/sql/user-management/fetchUserByUsername.sql");

            statement.setString(1, username);
            resultSet = statement.executeQuery();
        } catch (IOException | SQLException e) {
            log.error("cannot fetch user from database because of an unexpected an fatal error:" + e.getMessage());
            throw new InternalErrorException("cannot fetch user from database", e);
        }
        log.debug("fetched user from database");

        // convert ResultSet into user entity
        List<User> users = ResultSetProcessor.convert(User.class, resultSet);
        if (users.size() < 1) {
            log.warn("cannot fetch user from database: user with username not found");
            throw new NoSuchUserException(username);
        }

        return users.get(0);
    }

    public static void createNewUser(final User userData) throws InternalErrorException, UsernameAlreadyTakenException {
        log.debug("creating new user entity in database...");

        ResultSet resultSet;
        try {
            PreparedStatement pstmt = QueryHandler.loadStatement("");
            pstmt.setString(1, userData.getUsername());
            pstmt.setString(2, userData.getEmail());
            pstmt.setString(3, userData.getPasswordHash());

            resultSet = pstmt.executeQuery();
        } catch (IOException | SQLException e) {
            log.error("cannot create user to database because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot create user", e);
        }

        log.debug("created user to database");
    }

    public static boolean isUniqueUsername(final String username) {
        log.debug("checking for already existing username in database");
        return false;
    }
}
