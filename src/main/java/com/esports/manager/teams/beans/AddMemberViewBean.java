package com.esports.manager.teams.beans;

import com.esports.manager.userManagement.entities.User;

import java.io.Serializable;
import java.util.List;

public class AddMemberViewBean implements Serializable {
    private List<User> users;

    private Long teamId;

    public AddMemberViewBean() {}

    public AddMemberViewBean(List<User> users, Long teamID) {
        this.users = users;
        this.teamId = teamID;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
