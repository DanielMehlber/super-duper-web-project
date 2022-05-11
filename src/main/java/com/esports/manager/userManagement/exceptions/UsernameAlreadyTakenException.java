package com.esports.manager.userManagement.exceptions;

/**
 * This exception is thrown if the requested username is already in use and not available for use.
 * @author Daniel Mehlber
 */
public class UsernameAlreadyTakenException extends Exception {

    public UsernameAlreadyTakenException() {
        super("The requested username is already taken and therefor not available");
    }

}
