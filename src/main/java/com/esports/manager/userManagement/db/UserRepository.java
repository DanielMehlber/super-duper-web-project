package com.esports.manager.userManagement.db;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.UsernameAlreadyTakenException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Database interactions with user entities.
 */
public class UserRepository {

    private static final Logger log = LogManager.getLogger(UserRepository.class);

    public static User getByUsername(final String username) throws InternalErrorException, NoSuchUserException {
        log.debug("fetching user with username '%s' entity by username from database...");
        return null;
    }

    public static void createNewUser(final User userData) throws InternalErrorException, UsernameAlreadyTakenException {
        log.debug("creating new user entity in database...");
    }

}
