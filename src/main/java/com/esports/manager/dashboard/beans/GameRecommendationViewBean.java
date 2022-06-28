package com.esports.manager.dashboard.beans;

import com.esports.manager.games.entities.Game;

import java.io.Serializable;
import java.util.Objects;

/**
 * This View Bean Contains all information for the game recommendation on the dashboard
 * @author Daniel Mehlber
 */
public class GameRecommendationViewBean implements Serializable {

    private Game game;

    private int teamsCount;

    private int playerCount;

    public GameRecommendationViewBean() {}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getTeamsCount() {
        return teamsCount;
    }

    public void setTeamsCount(int teamsCount) {
        this.teamsCount = teamsCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecommendationViewBean that = (GameRecommendationViewBean) o;
        return teamsCount == that.teamsCount && playerCount == that.playerCount && Objects.equals(game, that.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, teamsCount, playerCount);
    }
}
