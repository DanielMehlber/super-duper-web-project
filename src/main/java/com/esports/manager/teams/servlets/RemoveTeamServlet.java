package com.esports.manager.teams.servlets;

import com.esports.manager.teams.TeamManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/teams/removeTeam")
public class RemoveTeamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(RemoveTeamServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long teamId = Long.valueOf(req.getParameter("teamId"));
        TeamManagement.removeTeamByTeamId(teamId);

        resp.sendRedirect(getServletContext().getContextPath() + "/teams");
    }
}
