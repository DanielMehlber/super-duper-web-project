package com.esports.manager.userManagement.logic;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * collection of methods that will be used in user management
 *
 * @author Daniel Mehlber,
 */
public class UserManagement {

    private static Logger log = LogManager.getLogger(UserManagement.class);

    /**
     * Checks if the passed username is available. In order to do so, it attempts to fetch a user with this
     * username and checks if the operation was successful.
     * 
     * @param username username to check
     * @return true if the username is available
     * @throws InternalErrorException some unexpected and fatal internal error occurred
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

}