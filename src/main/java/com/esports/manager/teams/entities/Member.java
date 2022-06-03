package com.esports.manager.teams.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

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
}
