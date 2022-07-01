package com.esports.manager.dashboard.servlets;

import com.esports.manager.dashboard.beans.DashboardViewBean;
import com.esports.manager.dashboard.beans.GameRecommendationViewBean;
import com.esports.manager.games.Games;
import com.esports.manager.games.entities.Game;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Servlet setting up the dashboard in order to display the JSP
 * @author Daniel Mehlber
 */
@WebServlet("/dashboard")
public class DashboardControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger log = LogManager.getLogger(DashboardControllerServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = UserManagement.getAuthorizedUser(request.getSession());

        // for security fetch user again
        try {
            currentUser = UserManagement.fetchUserByUsername(currentUser.getUsername());
        } catch (NoSuchUserException e) {
            // user does not exist anymore, log out user and redirect him to login page
            log.warn(String.format("user '%s' was logged in, but does not exist anymore. The user was logged out.", currentUser.getUsername()));
            UserManagement.performLogout();
            response.sendRedirect("/");
        }

        DashboardViewBean dashboardViewBean = new DashboardViewBean();
        dashboardViewBean.setUsername(currentUser.getUsername());

        // game recommendation tile
        setupGameRecommendation(dashboardViewBean);

        request.setAttribute("dashboardBean", dashboardViewBean);
        request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
    }

    private void setupGameRecommendation(DashboardViewBean dashboardViewBean) throws InternalErrorException {
        Game randomGame = Games.getRandomGame();

        // if there is no game to be recommended, the attribute will be null.
        if(randomGame != null) {
            GameRecommendationViewBean gameRecommendationViewBean = new GameRecommendationViewBean();
            gameRecommendationViewBean.setGame(randomGame);
            // get amount of teams
            List<Team> teams = Games.getTeamsWithGame(randomGame);
            gameRecommendationViewBean.setTeamsCount(teams.size());

            // get amount of players in all teams
            Set<Member> players = new HashSet<>();
            for(Team team : teams) {
                List<Member> teamMembers = TeamManagement.fetchMembersByTeamId(team.getId());
                players.addAll(teamMembers);
            }
            gameRecommendationViewBean.setPlayerCount(players.size());

            dashboardViewBean.setGameRecommendation(gameRecommendationViewBean);
        }
    }
}
