package com.esports.manager.newsfeed.entities;

import com.esports.manager.global.db.mapping.ResultSetMapping;

import java.io.Serializable;
import java.util.Date;

/**
 * represents a generic newsfeed item data object.
 * @author Daniel Mehlber
 */
public class NewsfeedItem implements Serializable {

    public static final String TYPE_PLAYER_JOIN = "player-join";
    public static final String TYPE_TEAM_CREATED = "team-created";

    @ResultSetMapping("id")
    private Long id;

    @ResultSetMapping("type")
    private String type;

    @ResultSetMapping("date")
    private Date date;

    @ResultSetMapping("player1")
    private String player1Id;

    @ResultSetMapping("player2")
    private String player2Id;

    @ResultSetMapping("team1")
    private Long team1Id;

    @ResultSetMapping("team2")
    private Long team2Id;

    public NewsfeedItem() {}

    public NewsfeedItem(final Date date, final String type, final String player1Id, final String player2Id, final Long team1Id, final Long team2Id) {
        setDate(date);
        setType(type);
        setPlayer1Id(player1Id);
        setPlayer2Id(player2Id);
        setTeam1Id(team1Id);
        setTeam2Id(team2Id);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(String player1Id) {
        this.player1Id = player1Id;
    }

    public String getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(String player2Id) {
        this.player2Id = player2Id;
    }

    public Long getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(Long team1Id) {
        this.team1Id = team1Id;
    }

    public Long getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(Long team2Id) {
        this.team2Id = team2Id;
    }

    public String toJson() {
        return String.format("{\"type\":\"%s\", \"date\":\"%s\", \"player1\": \"%s\", \"player2\": \"%s\", \"team1\":%d, \"team2\":%d}",
                type, date.toString(), player1Id, player2Id, team1Id, team2Id);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
