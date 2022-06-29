package com.esports.manager.teams.beans;

import com.esports.manager.games.entities.Game;

import java.util.List;

public class GamesViewBean {

    private List<Game> games;


    public GamesViewBean(){
    }

    public GamesViewBean(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }
}
