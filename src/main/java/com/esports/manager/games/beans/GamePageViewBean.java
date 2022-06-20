package com.esports.manager.games.beans;

import com.esports.manager.games.entities.Game;

import java.io.Serializable;

/**
 * View Bean for Game View
 * @author Daniel Mehlber
 */
public class GamePageViewBean implements Serializable {

    private Game game;

    public GamePageViewBean() {}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
