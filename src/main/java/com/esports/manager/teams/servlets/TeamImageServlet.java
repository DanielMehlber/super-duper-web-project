package com.esports.manager.teams.servlets;

import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.teams.exceptions.NoSuchTeamException;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;
import com.esports.manager.teams.logic.Teams;
import com.esports.manager.userManagement.exceptions.NoImageFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/teams/images")
@MultipartConfig(maxFileSize = 1024*1024*10) // 10mb max
public class TeamImageServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger(TeamImageServlet.class);

    /**
     * Loads team image (background and profile picture)
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException an internal error has occurred and will be displayed in an error page
     * @throws IOException an IO error occurred and will be displayed in an error page
     * @author Maximilian Rublik
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            log.warn("image was requested, but team id was wrong");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "team id was wrong");
            return;
        }

        String type = req.getParameter("type");
        type = type == null ? "profile" : type;

        // fetch team data
        Team team;
        try {
            team = Teams.fetchTeamById(id);
        } catch (NoSuchTeamException e) {
            log.warn("image of non-existent team was requested");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "no picture found");
            return;
        }

        byte[] image;
        try {
            if (type.toLowerCase().equals("profile")) {
                image = TeamRepository.loadProfileImage(id);
            } else if (type.toLowerCase().equals("background")){
                image = TeamRepository.loadBackgroundImage(id);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "unknown team image type");
                return;
            }
        } catch (NoImageFoundException e) {
            resp.sendError(HttpServletResponse.SC_NO_CONTENT, "No image found");
            return;
        }

        resp.setContentType("image/png");
        resp.getOutputStream().write(image);
        resp.getOutputStream().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: not sure whether we really need it here
    }
}
