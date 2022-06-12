package com.esports.manager.teams.servlets;

import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.beans.TeamsViewBean;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet("/teams/search")
public class TeamsSearchServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(TeamsSearchServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        final User loggedInUser = UserManagement.getAuthorizedUser(req.getSession());

        final String teamSearchPattern = req.getParameter("un");

        List<Team> teams;
        if (teamSearchPattern == null || teamSearchPattern.isBlank()) {
            log.debug("team search: no pattern was provided, fetching all teams");
            try {
                teams = TeamManagement.fetchAllTeams();
            } catch (NoTeamsFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            teams = TeamManagement.fetchTeamByNamePattern(teamSearchPattern);
        }

        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            String optionalComma = i+1 == teams.size() ? "" : ",";

            String teamJson = String.format("{\"name\":\"%s\", \"id\":\"%s\", \"tags\":\"%s\", \"slogan\":\"%s\"}%s",
                team, team.getId(), team.getTags(), team.getSlogan(), optionalComma);
            jsonBuilder.append(teamJson);
        }

        jsonBuilder.append("]");
        String json = jsonBuilder.toString();
        resp.getWriter().println(json);
    }
}
