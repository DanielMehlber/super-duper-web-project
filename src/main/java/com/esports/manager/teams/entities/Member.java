package com.esports.manager.teams.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

import java.util.Objects;

public class Member {
    @ResultSetMapping("username")
    private String username;

    @ResultSetMapping("teamId")
    private Long teamId;

    @ResultSetMapping("since")
    private String since;

    @ResultSetMapping("role")
    private String role;

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
