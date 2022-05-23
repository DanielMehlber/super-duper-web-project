package com.esports.manager.teams.servlets;

import com.esports.manager.teams.beans.TeamsViewBean;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;
import com.esports.manager.teams.logic.Teams;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/teams")
public class TeamsServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(TeamsServlet.class);
    
    /**
     * Loads a list of teams 
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException an internal error has occurred and will be displayed in an error page
     * @throws IOException an IO error occurred and will be displayed in an error page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamsViewBean teamsViewBean = new TeamsViewBean();

        try {
            teamsViewBean.setTeams(Teams.fetchAllTeams());
        } catch (NoTeamsFoundException e) {
            teamsViewBean.setErrorMessage("No teams found");
            throw new RuntimeException(e);
        } finally {
            req.setAttribute("teamsBean", teamsViewBean);
        }

        RequestDispatcher rq = req.getRequestDispatcher("/jsp/teams.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
