package com.esports.manager.memberArea.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

/**
 * The Team entity contains all user data of a specific user.
 * @author Maximilian Rublik
 */
public class Team {
    @ResultSetMapping("name")
    private String name;

    @ResultSetMapping("slogan")
    private String slogan;

    @ResultSetMapping("profile_picture")
    private String profilePicture;

    @ResultSetMapping("background_picture")
    private String backgroundPicture;

    @ResultSetMapping("tags")
    private String tags;

    public Team(String name, String slogan, String profilePicture, String backgroundPicture, String tags){
        this.backgroundPicture = backgroundPicture;
        this.name = name;
        this.profilePicture = profilePicture;
        this.slogan = slogan;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
