package com.esports.manager.teams.servlets;

import com.esports.manager.teams.logic.TeamManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.logic.UserManagement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("teams/removemember")
public class RemoveMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1;

    private final Logger log = LogManager.getLogger(RemoveMemberServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserManagement.getAuthorizedUser(req.getSession());

        if (user != null) {
            Long teamId = Long.valueOf(req.getParameter("teamid"));
            String username = req.getParameter("username");

            TeamManagement.removeUserFromTeam(username, teamId);

            resp.sendRedirect(getServletContext().getContextPath() + "/teams/team?id=" + teamId);
        }
    }
}
