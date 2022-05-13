package com.esports.manager.userManagement.exceptions;

/**
 * is thrown when the login credentials are wrong
 * @author Philipp Phan
 */
public class WrongCredentialsException extends Exception {

    public WrongCredentialsException() {
        super("wrong credentials were passed");
    }

}
