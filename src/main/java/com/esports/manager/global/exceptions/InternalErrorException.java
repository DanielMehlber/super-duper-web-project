package com.esports.manager.global.exceptions;

import jakarta.servlet.ServletException;

/**
 * This error will be thrown if an unexpected and fatal internal error occurred that may be impossible to
 * escalate/handle. This exception can be thrown by the servlet because its derived from {@link ServletException}
 * @author Daniel Mehlber
 */
public class InternalErrorException extends ServletException {

    public InternalErrorException(String message, final Throwable cause) {
        super(String.format("an internal error occurred: %s", message), cause);
    }

    public InternalErrorException(String message) {
        super(String.format("an internal error occurred: %s", message));
    }

}
