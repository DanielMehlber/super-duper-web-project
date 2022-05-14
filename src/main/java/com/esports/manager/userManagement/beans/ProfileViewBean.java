package com.esports.manager.userManagement.beans;

import java.io.Serializable;

public class ProfileViewBean implements Serializable {
    public ProfileViewBean(){}

    //TODO Change all attributes to User Object
    private String username;
    private String email;
    private String team;
    private String game;
    private String role;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public Long getPoints(){
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}
