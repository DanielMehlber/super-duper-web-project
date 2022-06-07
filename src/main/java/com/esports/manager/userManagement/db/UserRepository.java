package com.esports.manager.userManagement.db;

import com.esports.manager.global.db.mapping.ResultSetProcessor;
import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoImageFoundException;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Database interactions with user entities
 * @author Daniel Mehlber, Maximilian Rublik, Philipp Phan
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

        List<User> users;
        // execute query and attempt to fetch user from database
        ResultSet resultSet;
        try(PreparedStatement statement = QueryHandler.loadStatement("/sql/user-management/fetchUserByUsername.sql");
            Connection connection = statement.getConnection()) {
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            // convert ResultSet into user entity
            users = ResultSetProcessor.convert(User.class, resultSet);
        } catch (IOException | SQLException e) {
            log.error("cannot fetch user from database because of an unexpected an fatal error:" + e.getMessage());
            throw new InternalErrorException("cannot fetch user from database", e);
        }
        log.debug("fetched user from database");

        // check if a user with username was found
        if (users.size() < 1) {
            log.warn("cannot fetch user from database: user with username not found");
            throw new NoSuchUserException(username);
        }

        return users.get(0);
    }

    /**
     * Creates new user in database
     * @param userData user data to persist
     * @throws InternalErrorException an internal database error occurred
     * @author Maximilian Rublik
     */
    public static void createNewUser(final User userData) throws InternalErrorException {
        log.debug("creating new user entity in database...");

        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/user-management/createUser.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setString(1, userData.getUsername());
            pstmt.setString(2, userData.getEmail());
            pstmt.setString(3, userData.getPasswordHash());

            pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            log.error("cannot create user to database because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot create user", e);
        }

        log.debug("created user to database");
    }

    /**
     * Checks if username is available and not taken by another user
     * @param username name to check
     * @return true if username is available and unique
     * @throws InternalErrorException an internal database error occurred
     * @author Daniel Mehlber
     */
    public static boolean isUniqueUsername(final String username) throws InternalErrorException {
        log.debug("checking for already existing username in database");

        // try to fetch user with username
        boolean isUsernameUnique = false;
        try {
            getByUsername(username);
        } catch (NoSuchUserException e) {
            isUsernameUnique = true;
        }

        return isUsernameUnique;
    }


    /**
     * Loads profile image of user with username
     * @param username username of user
     * @return bytes of image
     * @throws InternalErrorException cannot fetch from database; connection error; sql error
     * @throws NoImageFoundException there is no profile picture for this username
     * @author Daniel Mehlber
     */
    public static byte[] loadProfileImage(final String username) throws InternalErrorException, NoImageFoundException {
        log.debug("loading profile image");

        byte[] image;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/user-management/fetchUserProfileImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setString(1, username);
            ResultSet result = pstmt.executeQuery();

            // check if a image for this username has been found
            if(result.next()) {
                image = result.getBytes(1);
            } else {
                log.warn("no profile picture found for username");
                throw new NoImageFoundException(username, "profile");
            }
        } catch (IOException | SQLException e) {
            log.error("cannot fetch profile picture because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch profile image", e);
        } catch (RuntimeException e) {
            log.error("cannot fetch profile picture because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch profile image", e);
        }

        if(image == null) {
            log.warn("user " + username + " has no profile image");
            throw new NoImageFoundException(username, "profile");
        }

        return image;
    }

    /**
     * Loads background image of user with username
     * @param username username of user
     * @return bytes of image
     * @throws InternalErrorException cannot fetch from database; connection error; sql error
     * @throws NoImageFoundException there is no background picture for this username
     * @author Daniel Mehlber
     */
    public static byte[] loadBackgroundImage(final String username) throws InternalErrorException, NoImageFoundException {
        log.debug("loading background image");

        byte[] image;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/user-management/fetchUserBackgroundImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setString(1, username);
            ResultSet result = pstmt.executeQuery();

            // check if a image for this username has been found
            if(result.next()) {
                image = result.getBytes(1);
            } else {
                log.warn("no background picture found for username");
                throw new NoImageFoundException(username, "background");
            }
        } catch (IOException | SQLException e) {
            log.error("cannot fetch background picture because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch background image", e);
        } catch (RuntimeException e) {
            log.error("cannot fetch background picture because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch background image", e);
        }

        if(image == null) {
            log.warn("user '" + username + "' has no background image");
            throw new NoImageFoundException(username, "background");
        }

        return image;
    }

    /**
     * Sets background image of user
     * @param image image data as byte array
     * @param user images will belong to this user
     * @throws InternalErrorException cannot write to database
     * @author Daniel Mehlber
     */
    public static void setBackgroundImage(final byte[] image, User user) throws InternalErrorException {
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/user-management/setUserBackgroundImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setBytes(1, image);
            pstmt.setString(2, user.getUsername());
            pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            log.error("cannot set background picture because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot set background image", e);
        } catch (RuntimeException e) {
            log.error("cannot set background picture because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot set background image", e);
        }
    }

    /**
     * Sets profile image of user
     * @param image image data as byte array
     * @param user images will belong to this user
     * @throws InternalErrorException cannot write to database
     * @author Daniel Mehlber
     */
    public static void setProfileImage(final byte[] image, User user) throws InternalErrorException {
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/user-management/setUserProfileImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setBytes(1, image);
            pstmt.setString(2, user.getUsername());
            pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            log.error("cannot set profile picture because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot set profile image", e);
        } catch (RuntimeException e) {
            log.error("cannot set profile picture because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot set profile image", e);
        }
    }

    /**
     * Fetches user entities with regex pattern matching username
     * @param pattern regex pattern
     * @return list of users with username matching regex pattern
     * @throws InternalErrorException an unexpected internal error occurred
     * @author Daniel Mehlber
     */
    public static List<User> fetchAllUserWithUsernamePattern(final String pattern) throws InternalErrorException {
        log.debug("loading background image");

        List<User> users;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/user-management/fetchUserByUsernamePattern.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setString(1, pattern);
            ResultSet result = pstmt.executeQuery();

            // convert resultset into users
            users = ResultSetProcessor.convert(User.class, result);
        } catch (IOException | SQLException e) {
            log.error("cannot fetch users with username pattern because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch users with username pattern", e);
        } catch (RuntimeException e) {
            log.error("cannot fetch users with username pattern because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch users with username pattern", e);
        }

        return users;

    }
}
