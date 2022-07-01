package com.esports.manager.teams.servlets;

import com.esports.manager.teams.TeamManagement;
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

/**
 * Servlet for removing members from the system
 * @author Maximilian Rublik
 */
@WebServlet("teams/removeMember")
public class RemoveMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger log = LogManager.getLogger(RemoveMemberServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedInUser = UserManagement.getAuthorizedUser(req.getSession());

        Long teamId = Long.valueOf(req.getParameter("id"));
        String username = req.getParameter("username");

        TeamManagement.removeUserFromTeam(username, teamId);
        resp.sendRedirect(getServletContext().getContextPath() + "/teams/team?id=" + teamId);
    }
}
