package com.esports.manager.userManagement.beans;

import com.esports.manager.userManagement.entities.User;

import java.io.Serializable;

public class LoginBean implements Serializable {
    private static final long servialVersionUID = 1L;
    private String username;
    private String password;
    private String errorMessage = "Hallo";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
