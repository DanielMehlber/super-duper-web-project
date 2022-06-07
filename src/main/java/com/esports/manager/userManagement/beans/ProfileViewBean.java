package com.esports.manager.userManagement.beans;

import com.esports.manager.userManagement.entities.User;

import java.io.Serializable;

public class ProfileViewBean implements Serializable {
    public ProfileViewBean() {
    }

    private Boolean editPermission;
    private User user;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getEditPermission(){
        return editPermission;
    }

    public void setEditPermission(Boolean editPermission){
        this.editPermission = editPermission;
    }
}