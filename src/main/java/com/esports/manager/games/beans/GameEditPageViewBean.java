package com.esports.manager.games.beans;

import java.io.Serializable;

/**
 * This bean contains information for the edit page View to display (e.g. error due to invalid input)
 * @author Daniel Mehlber
 */
public class GameEditPageViewBean implements Serializable {

    private String error;

    public GameEditPageViewBean() {}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // TODO: equals and hash code
}
