package com.esports.manager.teams.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(slogan, team.slogan) && Objects.equals(tags, team.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slogan, tags);
    }
}
