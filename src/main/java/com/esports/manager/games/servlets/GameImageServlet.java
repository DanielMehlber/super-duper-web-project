package com.esports.manager.games.servlets;


import com.esports.manager.games.Games;
import com.esports.manager.games.db.GamesRepository;
import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.NoSuchGameException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.games.db.GameImageRepository;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoImageFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This servlet lets the user load and save profile images
 */
@WebServlet("/games/images")
@MultipartConfig(maxFileSize = 1024*1024*10) // 10mb at most
public class GameImageServlet extends HttpServlet {

    private final Logger log = LogManager.getLogger(GameImageServlet.class);

    /**
     * Loads game image (background and profile picture)
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException an internal error has occurred and will be displayed in an error page
     * @throws IOException an IO error occurred and will be displayed in an error page
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // pull parameters
        String parameterId = request.getParameter("id");
        if(parameterId == null || parameterId.isBlank()) {
            log.warn("image was requested, but no game id was provided");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game id is missing");
            return;
        }

        Long id;
        try {
            id = Long.valueOf(parameterId);
        } catch (RuntimeException e) {
            log.warn(String.format("cannot load image: passed game-id parameter is not a valid number"));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game id is NaN");
            return;
        }

        String type = request.getParameter("type");
        type = type == null ? "profile" : type;

        // fetch user data
        Game game;
        try {
            game = Games.fetchById(id);
        } catch (NoSuchGameException e) {
            // user not found
            log.warn("cannot fetch image of game: image for non-existent game was requested");
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "game does not exist");
            return;
        }

        byte[] image;

        if (type.equalsIgnoreCase("profile")) {
            try {
                image = GameImageRepository.loadProfileImage(game);
            } catch (NoImageFoundException e) {
                // no profile image was found in database for user, send default one
                log.debug(String.format("no profile picture found for game id:%d, redirecting to default image", game.getId()));
                response.sendRedirect(getServletContext().getContextPath() + "/img/default-game-pb.png");
                return;
            }
        } else if (type.equalsIgnoreCase("background")) {
            // TODO: return value based (not exception based): just return null and check for it.
            try {
                image = GameImageRepository.loadBackgroundImage(game);
            } catch (NoImageFoundException e) {
                // no background image was found in database for user, send default one
                log.debug(String.format("no background picture found for game id:%d, redirecting to default image", game.getId()));
                response.sendRedirect(getServletContext().getContextPath() + "/img/default-game-bg.jpg");
                return;
            }
        } else {
            log.warn("cannot fetch image because of unknown type");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "unknown game image type");
            return;
        }


        response.setContentType("image/png");
        response.getOutputStream().write(image);
        response.getOutputStream().flush();
    }

    /**
     * Sets game image
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     *                Must contain the image as multipart.
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException cannot load from database
     * @throws IOException some other IO error occurred
     * @author Daniel Mehlber
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
        // check if user is logged in
        User user = UserManagement.getAuthorizedUser(request.getSession());

        // TODO: Check if user is team loader/admin

        // get type from request (is "profile", if parameter was not specified)
        String type = request.getParameter("type");
        type = type == null ? "profile" : type;

        String parameterId = request.getParameter("id");
        if(parameterId == null || parameterId.isBlank()) {
            log.warn("cannot set image of game: parameter game id is missing");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game id is missing");
            return;
        }

        Long id;
        try {
            id = Long.valueOf(parameterId);
        } catch (NumberFormatException e) {
            log.warn("cannot set image of game: game id is not valid number");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game id is NaN");
            return;
        }

        Game game;
        try {
            game = GamesRepository.fetchById(id);
        } catch (NoSuchGameException e) {
            log.warn(String.format("cannot set image of game: game with id:%d does not exist", id));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game with passed id does not exist");
            return;
        }

        // read image as byte array from request
        Part imagePart = request.getPart("image");
        InputStream is = imagePart.getInputStream();

        // vvv Start Copy https://stackoverflow.com/a/1264737 vvv
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        // ^^^ End Copy ^^^

        byte[] image = buffer.toByteArray();

        if(type.equalsIgnoreCase("profile")) {
            GameImageRepository.setProfileImage(image, game);
        } else if (type.equalsIgnoreCase("background")) {
            GameImageRepository.setBackgroundImage(image, game);
        } else {
            log.warn(String.format("cannot upload image for game with unknown type '%s'", type));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game image type unknown");
            return;
        }

        response.sendRedirect(getServletContext().getContextPath()+"/games/game?id="+game.getId());
        log.info("game image uploaded");
    }
}
