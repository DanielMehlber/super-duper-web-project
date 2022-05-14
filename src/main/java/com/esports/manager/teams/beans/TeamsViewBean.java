package com.esports.manager.teams.beans;

import com.esports.manager.teams.entities.Team;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Maximilian Rublik
 */
public class TeamsViewBean implements Serializable
{
    private List<Team> teams;

    private String errorMessage;

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
