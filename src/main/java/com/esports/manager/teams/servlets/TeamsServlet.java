package com.esports.manager.teams.servlets;

import com.esports.manager.games.Games;
import com.esports.manager.games.entities.Game;
import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.beans.GamesViewBean;
import com.esports.manager.teams.beans.TeamsViewBean;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @author Maximilian Rublik
 */
@WebServlet("/teams")
public class TeamsServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(TeamsServlet.class);
    
    /**
     * Loads a list of teams 
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException an internal error has occurred and will be displayed in an error page
     * @throws IOException an IO error occurred and will be displayed in an error page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedinUser = UserManagement.getAuthorizedUser(req.getSession());

        GamesViewBean gamesViewBean = new GamesViewBean();
        gamesViewBean.setGames(Games.searchGame(".*"));
        req.setAttribute("gamesViewBean", gamesViewBean);

        RequestDispatcher rq = req.getRequestDispatcher("/jsp/teams.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
