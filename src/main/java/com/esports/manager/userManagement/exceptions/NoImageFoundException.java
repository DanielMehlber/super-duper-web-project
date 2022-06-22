package com.esports.manager.userManagement.exceptions;

import com.esports.manager.games.entities.Game;

/**
 * This exception is thrown if the user has no profile picture or no picture was found
 * @author Daniel Mehlber
 */
public class NoImageFoundException extends Exception {

    public NoImageFoundException(String username, String type) {
        super("no "+type+" image for user '" + username + "' was found");
    }

    public NoImageFoundException(Game game, String type) {
        super(String.format("no %s image found for game with id:%d", type, game.getId()));
    }

}
