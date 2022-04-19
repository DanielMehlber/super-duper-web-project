package com.esports.manager.userManagement.exceptions;

/**
 * this exception is thrown when a passed userId is not known or associated with an existing user.
 *
 * @author Daniel Mehlber
 */
public class NoSuchUserException extends Exception {

    public NoSuchUserException(final String username) {
        super(String.format("user with username '%s' was requested, but does not exist", username));
    }

}
