package com.esports.manager.teams.beans;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Maximilian Rublik
 */
public class TeamsViewBean implements Serializable
{
    private Map<Team, List<Member>> teams;

    private String errorMessage;

    public TeamsViewBean() {
    }

    public Map<Team, List<Member>> getTeams() {
        if (this.teams == null) {
            this.teams = new HashMap<>();
        }

        return this.teams;
    }

    public void setTeams(List<Team> teams) throws InternalErrorException {
        for (Team team : teams) {
            getTeams().put(team, TeamRepository.getMemberByTeamId(team.getId()));
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
