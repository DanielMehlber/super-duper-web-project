package com.esports.manager.teams.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;


/**
 * The Team entity contains all user data of a specific user.
 * @author Maximilian Rublik
 */
public class Team {
    @ResultSetMapping("id")
    private Long id;

    @ResultSetMapping("name")
    private String name;

    @ResultSetMapping("slogan")
    private String slogan;

    @ResultSetMapping("tags")
    private String tags;

    public Team(String name, String slogan, String tags){
        this.name = name;
        this.slogan = slogan;
        this.tags = tags;
    }

    public Team() {}

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
