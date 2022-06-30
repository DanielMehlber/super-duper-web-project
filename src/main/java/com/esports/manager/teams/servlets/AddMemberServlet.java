package com.esports.manager.teams.servlets;

import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.beans.AddMemberViewBean;
import com.esports.manager.teams.exceptions.NoSuchTeamException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;

/**
 * Add members to team
 * @author Maximilian Rublik
 */
@WebServlet("teams/addmember")
public class AddMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private final Logger log = LogManager.getLogger(AddMemberServlet.class);

    /**
     * Prepares the page with which a user gets added
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException database error; runtime error
     * @throws IOException io error; database error; sql statement read error
     * @author Maximilian Rublik
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * Adds user to team with a certain position
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException database error; runtime error
     * @throws IOException io error; database error; sql statement read error
     * @author Maximilian Rublik
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedinUser = UserManagement.getAuthorizedUser(req.getSession());
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("users");
        Long teamId = Long.valueOf(req.getParameter("teamId"));
        String role = req.getParameter("position");
        java.sql.Date since = new Date(System.currentTimeMillis());

        try {
            TeamManagement.addUserToTeam(username, teamId, role, since);
        } catch (NoSuchTeamException e) {
            // TODO: Maxi handle this
        } catch (NoSuchUserException e) {
            // TODO: Maxi handle this
        }

        RequestDispatcher rq = req.getRequestDispatcher(getServletContext().getContextPath() + "/teams/team?id="+ teamId);
        resp.sendRedirect(getServletContext().getContextPath() + "/teams/team?id="+ teamId);
    }
}
