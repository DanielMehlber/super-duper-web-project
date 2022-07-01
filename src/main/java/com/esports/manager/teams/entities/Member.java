package com.esports.manager.teams.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

import java.util.Objects;

/*@author Maximilian Rublik*/

public class Member {
    @ResultSetMapping("username")
    private String username;

    @ResultSetMapping("teamId")
    private Long teamId;

    @ResultSetMapping("since")
    private String since;

    @ResultSetMapping("isTeamLeader")
    private Boolean isTeamLeader;

    @ResultSetMapping("role")
    private String role;

    public Member() {}

    public Member(final String username, final Long teamId, String since, String role) {
        this.role = role;
        this.username = username;
        this.teamId = teamId;
        this.since = since;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsTeamLeader() {
        return this.isTeamLeader;
    }

    public void setIsTeamLeader(boolean isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(username, member.username) && Objects.equals(teamId, member.teamId) && Objects.equals(role, member.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, teamId, role);
    }
}
