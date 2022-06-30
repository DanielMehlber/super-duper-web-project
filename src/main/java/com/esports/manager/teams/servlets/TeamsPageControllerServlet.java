package com.esports.manager.teams.servlets;

import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/teams")
public class TeamsPageControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserManagement.getAuthorizedUser(req.getSession());

        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/teams.jsp").forward(req, resp);
    }
}
