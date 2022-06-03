package com.esports.manager.teams.beans;

import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maximilian Rublik
 */
public class TeamViewBean implements Serializable {
    private String errorMessage;

    private Team team;

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
}
