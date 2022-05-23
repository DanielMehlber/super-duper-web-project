package com.esports.manager.teams.servlets;

import com.esports.manager.teams.beans.TeamViewBean;
import com.esports.manager.teams.db.TeamRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author Maximilian Rublik
 */

@WebServlet("/teams/team")
public class TeamServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(TeamsServlet.class);

    /**
     * Loads team by id which is given over the url, loads the team itself as its members
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException an internal error has occurred and will be displayed in an error page
     * @throws IOException an IO error occurred and will be displayed in an error page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Long id;

        TeamViewBean teamViewBean = new TeamViewBean();

        try {
            id = Long.parseLong(req.getParameter("id"));
            teamViewBean.setTeam(TeamRepository.getTeamById(id));
            teamViewBean.setMembers(TeamRepository.getMemberByTeamId(id));
        } catch (NumberFormatException e) {
            log.fatal("there was a problem casting the id parameter...");
            teamViewBean.setErrorMessage("The given team ID was incorrect..");
        } finally {
            req.setAttribute("teamViewBean", teamViewBean);

            RequestDispatcher rq = req.getRequestDispatcher("/jsp/team.jsp");
            rq.forward(req, resp);
        }
    }
}
