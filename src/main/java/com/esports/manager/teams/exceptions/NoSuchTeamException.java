package com.esports.manager.teams.exceptions;

/**
 * is thrown when the team is not found
 * @author Maximilian Rublik
 */
public class NoSuchTeamException extends Exception {
    public NoSuchTeamException() {
        super("user with id '%o' was requested, but does not exist");

    }
}
