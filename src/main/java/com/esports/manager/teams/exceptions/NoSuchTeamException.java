package com.esports.manager.teams.exceptions;

public class NoSuchTeamException extends Exception {
    public NoSuchTeamException() {
        super("user with id '%o' was requested, but does not exist");

    }
}
