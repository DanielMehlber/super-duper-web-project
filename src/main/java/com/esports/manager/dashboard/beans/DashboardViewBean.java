package com.esports.manager.dashboard.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 * Bean for the dashboard view JSP
 * @author Daniel Mehlber
 */
public class DashboardViewBean implements Serializable {

    private String username;

    private GameRecommendationViewBean gameRecommendation;

    public DashboardViewBean() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GameRecommendationViewBean getGameRecommendation() {
        return gameRecommendation;
    }

    public void setGameRecommendation(GameRecommendationViewBean gameRecommendation) {
        this.gameRecommendation = gameRecommendation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardViewBean that = (DashboardViewBean) o;
        return Objects.equals(username, that.username) && Objects.equals(gameRecommendation, that.gameRecommendation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, gameRecommendation);
    }
}
