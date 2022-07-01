package com.esports.manager.games.exceptions;

/**
 * Data of game is insufficient on create or update operation.
 * @author Daniel Mehlber
 */
public class GameDataInsufficientException extends Exception{

    public GameDataInsufficientException() {
        super("game data was insufficient");
    }

}
