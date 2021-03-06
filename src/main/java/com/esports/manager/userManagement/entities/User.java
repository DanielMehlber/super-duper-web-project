package com.esports.manager.userManagement.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

/**
 * The User entity contains all user data of a specific user.
 *
 * @author Philipp Phan
 */
public class User {

    @ResultSetMapping("username")
    private String username;

    @ResultSetMapping("email")
    private String email;

    @ResultSetMapping("passwordHash")
    private String passwordHash;

    @ResultSetMapping("nickname")
    private String nickname;

    @ResultSetMapping("isAdmin")
    private Boolean isAdmin;
    /**
     *to change user to admin:
     * UPDATE user SET isAdmin = 1 WHERE username = "username";
     * */


    public User() {
    }

    public User(String username, String email, String passwordHash, boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.isAdmin = isAdmin;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
