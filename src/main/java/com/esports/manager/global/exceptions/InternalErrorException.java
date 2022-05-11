package com.esports.manager.global.exceptions;

/**
 * This error will be thrown if an unexpected and fatal internal error occurred that may be impossible to
 * escalate/handle.
 * @author Daniel Mehlber
 */
public class InternalErrorException extends Exception {

    public InternalErrorException(String message, final Throwable cause) {
        super(String.format("an internal error occurred: %s", message), cause);
    }

    public InternalErrorException(String message) {
        super(String.format("an internal error occurred: %s", message));
    }

}
