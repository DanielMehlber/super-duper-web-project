package com.esports.manager.teams.servlets;

import com.esports.manager.teams.logic.Teams;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/teams/addnewteam")
public class AddNewTeamServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(AddNewTeamServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rq = req.getRequestDispatcher("/jsp/addnewteam.jsp");
        rq.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //TODO save images to database

        String teamname = req.getParameter("name");
        String slogan = req.getParameter("slogan");
        String profile = req.getParameter("profile");
        String background = req.getParameter("background");
        String tags = req.getParameter("tags");

        try {
            Teams.createTeam(teamname, slogan, tags, null);
            resp.sendRedirect(getServletContext().getContextPath() + "/teams");
        } catch (InvalidInputException e) {
            log.warn(String.format("cannot create team because of an invalid user input: %s", e.getMessage()), e);
            // TODO newBean.setErrorMessage("your input was not valid");
            throw new RuntimeException(e);
        }
    }
}
