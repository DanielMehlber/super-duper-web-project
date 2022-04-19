package com.esports.manager.global.exceptions;

/**
 * This error will be thrown if
 */
public class InternalErrorException extends Exception {

    public InternalErrorException(String message, final Throwable cause) {
        super(String.format("an internal error occured: %s", message), cause);
    }

}
