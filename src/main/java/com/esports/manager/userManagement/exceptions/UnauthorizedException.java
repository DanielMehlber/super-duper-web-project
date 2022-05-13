package com.esports.manager.userManagement.exceptions;

import jakarta.servlet.ServletException;

/**
 * This exception is thrown when user is unauthorized.
 * @author Daniel Mehlber
 */
public class UnauthorizedException extends ServletException {

    public UnauthorizedException() {
        super("interaction was not authorized");
    }

}
