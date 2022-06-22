package com.esports.manager.games.servlets;

import com.esports.manager.games.Games;
import com.esports.manager.games.beans.GamePageViewBean;
import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.NoSuchGameException;
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

@WebServlet("/games/game")
public class GamePageServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(GamePageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserManagement.getAuthorizedUser(req.getSession());

        String gameParameter = req.getParameter("id");
        String mode = req.getParameter("mode"); // 'view' or 'edit'
        if(mode == null || mode.isBlank() || (!mode.equals("edit") && !mode.equals("view")))
            mode = "view";

        if(gameParameter == null || gameParameter.isBlank()) {
            log.warn("cannot display game because id parameter is not provided");
            resp.sendRedirect(getServletContext().getContextPath() + "/games");
            return;
        }

        Long id;
        try {
            id = Long.valueOf(gameParameter);
        } catch (RuntimeException e) {
            log.warn(String.format("cannot display game because id was NaN (id:%s)", gameParameter));
            resp.sendRedirect(getServletContext().getContextPath() + "/games");
            return;
        }

        Game requestedGame;
        try {
            requestedGame = Games.fetchById(id);
        } catch (NoSuchGameException e) {
            log.warn(String.format("cannot display game because game with id:%d does not exist", id));
            resp.sendRedirect(getServletContext().getContextPath() + "/games");
            return;
        }

        GamePageViewBean gamePageViewBean = new GamePageViewBean();
        gamePageViewBean.setGame(requestedGame);
        req.setAttribute("gamePageViewBean", gamePageViewBean);

        if(mode.equals("edit")) {
            req.getRequestDispatcher("/jsp/game-edit.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/jsp/game-view.jsp").forward(req, resp);
        }
    }
}
