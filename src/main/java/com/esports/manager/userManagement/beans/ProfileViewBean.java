package com.esports.manager.userManagement.beans;

import com.esports.manager.userManagement.entities.User;

import java.io.Serializable;

/**
 * This ViewBean saves all data for the profile page
 * @author Philipp Phan
 */

public class ProfileViewBean implements Serializable {
    public ProfileViewBean() {
    }

    private Boolean editPermission;
    private Boolean isAdmin;
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

    public Boolean getEditPermission() {
        return editPermission;
    }

    public void setEditPermission(Boolean editPermission) {
        this.editPermission = editPermission;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
