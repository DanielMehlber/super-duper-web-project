package com.esports.manager.userManagement.logic;

import java.io.IOException;
import java.sql.SQLInput;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.mysql.cj.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.http.HttpSession;

/**
 * collection of methods that will be used in user management
 *
 * @author Daniel Mehlber,
 */
public class UserManagement {

    private static Logger log = LogManager.getLogger(UserManagement.class);

    /**
     * Checks if the passed username is available. In order to do so, it attempts to
     * fetch a user with this
     * username and checks if the operation was successful.
     * 
     * @param username username to check
     * @return true if the username is available
     * @throws InternalErrorException some unexpected and fatal internal error
     *                                occurred
     * @author Daniel Mehlber
     * @see UserManagement#fetchUserByUsername(String)
     */
    public static boolean isUsernameAvailable(final String username) throws InternalErrorException {
        log.debug("checking if username is available...");
        try {
            // attempt to fetch user with this username
            fetchUserByUsername(username);

            log.debug("username check complete: username is already taken and unavailable");

            // user fetched successfully, so the username is already taken
            return false;
        } catch (NoSuchUserException e) {
            // no user with this username found, so the username is available
            log.debug("username check complete: username is available");
            return true;
        }
    }

    public static User fetchUserByUsername(final String username) throws NoSuchUserException, InternalErrorException {
        log.debug("fetching user by username...");
        User user = UserRepository.getByUsername(username);
        return user;
    }

    //TODO: Log user out of active session
    public static void performLogout(){

    }
    /**
     * TODO
     * Checks if username and password (from html form) align with those in database
     * 
     * @author Philipp Phan
     */
    public static void performLogin(String username, String password)
            throws InternalErrorException, NoSuchUserException {
        try {
            User user = UserRepository.getByUsername(username);
            if (username.equals(user.getUsername()) && password.equals(user.getPasswordHash())) {
                //TODO: Add user-object to active session
                log.info("Login succcessfull. Welcome");
            } else {
                log.warn("LOGIN NOT SUCCESSFULL !!!!111!!!elf & Matze ist kleine eine B1tch");
            }
        } catch (InternalErrorException e) {
            log.error("Internal Error", e);
            throw e;
        } catch (NoSuchUserException e) {
            log.error("No user with username found", e);
            throw e;
        }
    }
}
