package com.esports.manager.memberArea.exceptions;

public class NoTeamsFoundException extends Exception {

    public NoTeamsFoundException() {
        super("all teams were requested, but no teams found");
    }
}
