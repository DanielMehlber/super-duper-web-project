package com.esports.manager.teams.servlets;

import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.teams.exceptions.NoSuchTeamException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
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

/**
 * Servlet for loading team images
 * @author Maximilian Rublik
 */
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
        User loggedinUser = UserManagement.getAuthorizedUser(req.getSession());
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
            team = TeamManagement.fetchTeamById(id);
        } catch (NoSuchTeamException e) {
            log.warn("image of non-existent team was requested");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "no picture found");
            return;
        }

        byte[] image;

        if (type.equalsIgnoreCase("profile")) {
            try {
                image = TeamRepository.loadProfileImage(id);
            } catch (NoImageFoundException e) {
                // no team image was found in database for team, send default one
                log.debug(String.format("no team picture found for team %s, redirecting to default image", team.getName()));
                resp.sendRedirect(getServletContext().getContextPath() + "/img/default-team-image.jpg");
                return;
            }
        } else if (type.equalsIgnoreCase("background")) {
            try {
                image = TeamRepository.loadBackgroundImage(team.getId());
            } catch (NoImageFoundException e) {
                // no background team image was found in database for team, send default one
                log.debug(String.format("no background picture found for team %s, redirecting to default image", team.getName()));
                resp.sendRedirect(getServletContext().getContextPath() + "/img/default-bg.jpg");
                return;
            }
        } else {
            log.warn("cannot fetch image because of unknown type");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "unknown team image type");
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
