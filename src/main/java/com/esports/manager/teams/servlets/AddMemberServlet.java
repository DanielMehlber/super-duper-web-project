package com.esports.manager.teams.servlets;

import com.esports.manager.teams.beans.AddMemberViewBean;
import com.esports.manager.teams.logic.TeamManagement;
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
import java.sql.Date;

/**
 * Add members to team
 * @author Maximilian Rublik
 */
@WebServlet("teams/addmember")
public class AddMemberServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private final Logger log = LogManager.getLogger(AddMemberServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddMemberViewBean addMemberViewBean = new AddMemberViewBean();
        Long teamId = Long.valueOf(req.getParameter("teamid"));
        addMemberViewBean.setUsers(UserManagement.fetchUserNotAlreadyMember(teamId));
        addMemberViewBean.setTeamId(teamId);
        req.setAttribute("addMemberViewBean", addMemberViewBean);

        RequestDispatcher rq = req.getRequestDispatcher("/jsp/addmember.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("users");
        Long teamId = Long.valueOf(req.getParameter("teamId"));
        String role = req.getParameter("position");
        java.sql.Date since = new Date(System.currentTimeMillis());

        TeamManagement.addUserToTeam(username, teamId, role, since);
        resp.sendRedirect(getServletContext().getContextPath() + "/teams");
    }
}
