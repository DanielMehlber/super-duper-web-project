package com.esports.manager.util.exceptions;

public class NotImplementedException extends RuntimeException {

    public NotImplementedException(String reason) {
        super("method not implemented: " + reason);
    }

}
