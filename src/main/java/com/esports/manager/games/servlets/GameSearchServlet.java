package com.esports.manager.games.servlets;

import com.esports.manager.games.Games;
import com.esports.manager.games.entities.Game;
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
import java.util.ArrayList;
import java.util.List;

/**
 * This servlet searches and returns game data in JSON format matching a certain regex pattern.
 * @author Daniel Mehlber
 */
@WebServlet("/games/search")
public class GameSearchServlet extends HttpServlet {

    private final Logger log = LogManager.getLogger(GameSearchServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        final User loggedInUser = UserManagement.getAuthorizedUser(req.getSession());

        // read parameters
        final String gameSearchPattern = req.getParameter("search");

        log.debug(String.format("user %s requested a game search with regex '%s'", loggedInUser.getUsername(), gameSearchPattern));

        // fetch users
        List<Game> games;
        if(gameSearchPattern == null || gameSearchPattern.isBlank()) {
            // get all games, no filter has been specified
            log.debug("user search: no pattern was provided, fetching all users");
            games = Games.searchGame(".*");
        } else {
            // search for game by regex pattern
            games = Games.search(gameSearchPattern);
        }

        log.debug(String.format("generating json containing %d found users", games.size()));

        // build json
        StringBuilder jsonBuilder = new StringBuilder("[");
        for(int i = 0; i < games.size(); i++) {
            Game game = games.get(i);

            String optionalComma = i+1 == games.size() ? "" : ",";

            // json of users
            String userJson = String.format("{\"id\":%d,\"title\":\"%s\"}%s",
                    game.getId(), game.getName(), optionalComma);
            jsonBuilder.append(userJson);
        }
        jsonBuilder.append("]");

        String json = jsonBuilder.toString();
        resp.getWriter().println(json);
    }
}
