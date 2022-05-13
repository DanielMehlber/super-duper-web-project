package com.esports.manager.userManagement.beans;

import java.io.Serializable;

/**
 * Represents data that will be used by the login jsp
 * @author Phlipp Phan
 */
public class LoginViewBean implements Serializable {
    private static final long servialVersionUID = 1L;
    private LoginViewBean(){}
    private String username;
    private String password;
    private String errorMessage;

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
