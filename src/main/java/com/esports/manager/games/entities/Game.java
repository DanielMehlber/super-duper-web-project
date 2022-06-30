package com.esports.manager.games.entities;


import com.esports.manager.global.db.mapping.ResultSetMapping;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity for Game object
 * @author Daniel Mehlber
 */
public class Game implements Serializable {

    @ResultSetMapping("id")
    private Long id;

    @ResultSetMapping("name")
    private String name;

    @ResultSetMapping("description")
    private String description;

    public Game() {}

    public Game(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id.equals(game.id) && Objects.equals(name, game.name) && Objects.equals(description, game.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
