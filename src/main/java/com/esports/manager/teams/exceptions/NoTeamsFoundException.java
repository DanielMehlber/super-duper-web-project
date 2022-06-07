package com.esports.manager.teams.exceptions;

/**
 * is thrown when no teams were found
 * @author Maximilian Rublik
 */
public class NoTeamsFoundException extends Exception {

    public NoTeamsFoundException() {
        super("all teams were requested, but no teams found");
    }
}
