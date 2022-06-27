package com.esports.manager.games.beans;

import com.esports.manager.games.entities.Game;
import com.esports.manager.teams.entities.Team;

import java.io.Serializable;
import java.util.List;

/**
 * View Bean for Game View
 * @author Daniel Mehlber
 */
public class GamePageViewBean implements Serializable {

    private Game game;
    private List<Team> teams;

    private int teamsCount;

    public GamePageViewBean() {}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
        teamsCount = teams.size();
    }

    public int getTeamsCount() {
        return teamsCount;
    }
}
