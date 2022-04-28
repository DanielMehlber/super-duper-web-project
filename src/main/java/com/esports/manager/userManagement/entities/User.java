package com.esports.manager.userManagement.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

/**
 * The User entity contains all user data of a specific user.
 */
public class User {

    @ResultSetMapping("username")
    private String username;

    @ResultSetMapping("passwordHash")
    private String passwordHash;

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
