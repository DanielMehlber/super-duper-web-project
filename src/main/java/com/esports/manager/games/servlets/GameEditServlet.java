package com.esports.manager.games.servlets;

import com.esports.manager.games.db.GamesRepository;
import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.NoSuchGameException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/games/game/edit")
public class GameEditServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(GameEditServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read parameter values
        String parameterId = request.getParameter("id");
        if(parameterId == null || parameterId.isBlank()) {
            log.warn("cannot change game data: parameter id not set");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game id is missing");
            return;
        }

        String parameterItem = request.getParameter("item");
        if(parameterItem == null || parameterItem.isBlank()) {
            log.warn("cannot change game data: item parameter not set");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "item parameter is missing");
        }

        Long id;
        try {
            id = Long.valueOf(parameterId);
        } catch (NumberFormatException e) {
            log.warn("cannot set data of game: game id is not valid number");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game id is NaN");
            return;
        }

        Game game;
        try {
            game = GamesRepository.fetchById(id);
        } catch (NoSuchGameException e) {
            log.warn(String.format("cannot set data of game: game with id:%d does not exist", id));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game with passed id does not exist");
            return;
        }

        String parameterValue = request.getParameter("value");
        if(parameterValue == null || parameterValue.isBlank()) {
            log.warn("cannot set game data: value was not set");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "no value specified");
            return;
        }

        if(parameterItem.equals("title")) {
            game.setName(parameterValue);
            GamesRepository.update(game);
        } else if (parameterItem.equals("description")) {
            game.setDescription(parameterValue);
            GamesRepository.update(game);
        } else {
            log.warn("cannot set data of game: unknown item value " + parameterItem);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game item unkown");
            return;
        }

        response.sendRedirect(getServletContext().getContextPath()+"/games/game?id="+game.getId());
        log.info("game data changed");

    }
}
