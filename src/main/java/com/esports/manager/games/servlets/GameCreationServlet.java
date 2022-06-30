package com.esports.manager.games.servlets;

import com.esports.manager.games.Games;
import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.GameDataInsufficientException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.UnauthorizedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Controller for the creation of a new servlet.
 * @author Daniel Mehlber
 */
@WebServlet("/games/new")
public class GameCreationServlet extends HttpServlet {

    private final Logger log = LogManager.getLogger(GameCreationServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User currentUser = UserManagement.getAuthorizedUser(req.getSession());

        if(!currentUser.getIsAdmin())
            throw new UnauthorizedException();

        String titleParameter = req.getParameter("title");
        if(titleParameter == null || titleParameter.isBlank()) {
            log.warn("cannot create game: no title parameter provided");
            resp.getWriter().println("no title provided");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Game game = null;
        try {
            game = Games.createGame(titleParameter, "");
        } catch (GameDataInsufficientException e) {
            log.warn("cannot create game: game data is insufficient");
            // just redirect back to game search
            // TODO: display some kind of error message on the game search page
            resp.sendRedirect(getServletContext().getContextPath() + "/games");
            return;
        }

        // redirect
        resp.sendRedirect(getServletContext().getContextPath() + "/games/game?id="+game.getId());
    }
}
