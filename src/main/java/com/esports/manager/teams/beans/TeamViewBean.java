package com.esports.manager.teams.beans;

import com.esports.manager.games.entities.Game;
import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maximilian Rublik
 */
public class TeamViewBean implements Serializable {
    private String errorMessage;

    private boolean currentUserIsAdmin;

    private Game game;

    private boolean hasGame;

    private Team team;

    private boolean isTeamLeader;

    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(boolean isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        if (game != null) {
            this.game = game;
            setHasGame(true);
        }
    }

    public boolean getHasGame() {
        return hasGame;
    }

    public void setHasGame(boolean hasGame) {
        this.hasGame = hasGame;
    }

    public boolean getCurrentUserIsAdmin() {
        return this.currentUserIsAdmin;
    }

    public void setCurrentUserIsAdmin(boolean isAdmin) {
        this.currentUserIsAdmin = isAdmin;
    }
}
