package com.esports.manager.games.exceptions;

/**
 * No such Game with id in database
 * @author Daniel Mehlber
 */
public class NoSuchGameException extends Exception {

    public NoSuchGameException(final long id) {
        super(String.format("no game with id:%d known to system", id));
    }

    public NoSuchGameException() {
        super("No game was found in database");
    }

}
