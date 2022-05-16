package com.esports.manager.userManagement.beans;

import java.io.Serializable;
import java.sql.Blob;

public class ProfileViewBean implements Serializable {
    public ProfileViewBean() {
    }

    //TODO Change all attributes to User Object

    private int id;
    private String username;
    private String email;

    private Blob profile_picture;

    private Blob background_picture;

    private String errorMessage;
    private Long points;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blob getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(Blob profile_picture) {
        this.profile_picture = profile_picture;
    }

    public Blob getBackground_picture() {
        return background_picture;
    }

    public void setBackground_picture(Blob background_picture) {
        this.background_picture = background_picture;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
