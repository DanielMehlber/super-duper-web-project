package com.esports.manager.games.servlets;

import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * This servlet controls access to the user page
 * @author Daniel Mehlber
 */
@WebServlet("/games")
public class GamesPageControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get authorized user from session
        User user = UserManagement.getAuthorizedUser(req.getSession());

        // put user object in session
        req.setAttribute("user", user);

        req.getRequestDispatcher("/jsp/game-search.jsp").forward(req, resp);
    }
}
