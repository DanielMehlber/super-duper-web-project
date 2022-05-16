package com.esports.manager.dashboard.beans;

import java.io.Serializable;

/**
 * Bean for the dashboard view JSP
 * @author Daniel Mehlber
 */
public class DashboardBean implements Serializable {

    private String username;

    public DashboardBean() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
