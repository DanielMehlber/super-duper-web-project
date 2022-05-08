package com.esports.manager.userManagement.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

/**
 * The User entity contains all user data of a specific user.
 * @author Daniel Mehlber
 */
public class User {

    @ResultSetMapping("username")
    private String username;

    @ResultSetMapping("username")
    private String email;

    @ResultSetMapping("passwordHash")
    private String passwordHash;

    public User() {}

    public User (String username, String email, String passwordHash){
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
