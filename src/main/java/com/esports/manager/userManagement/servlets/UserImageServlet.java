package com.esports.manager.userManagement.servlets;


import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoImageFoundException;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
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
 * @author Daniel Mehlber
 */
@WebServlet("/users/images")
@MultipartConfig(maxFileSize = 1024*1024*10) // 10mb at most
public class UserImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger log = LogManager.getLogger(UserImageServlet.class);

    /**
     * Loads user image (background and profile picture)
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException an internal error has occurred and will be displayed in an error page
     * @throws IOException an IO error occurred and will be displayed in an error page
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // pull parameters
        String username = request.getParameter("user");
        if(username == null || username.isBlank()) {
            log.warn("image was requested, but no user was provided");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "user is missing");
            return;
        }

        String type = request.getParameter("type");
        type = type == null ? "profile" : type;

        // fetch user data
        User user;
        try {
            user = UserManagement.fetchUserByUsername(username);
        } catch (NoSuchUserException e) {
            // user not found
            log.warn("image of non-existent user was requested");
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "no picture found");
            return;
        }

        byte[] image;

        if (type.equalsIgnoreCase("profile")) {
            try {
                image = UserRepository.loadProfileImage(username);
            } catch (NoImageFoundException e) {
                // no profile image was found in database for user, send default one
                log.debug(String.format("no profile picture found for user %s, redirecting to default image", user.getUsername()));
                response.sendRedirect(getServletContext().getContextPath() + "/img/default-pb.jpg");
                return;
            }
        } else if (type.equalsIgnoreCase("background")) {
            try {
                image = UserRepository.loadBackgroundImage(username);
            } catch (NoImageFoundException e) {
                // no background image was found in database for user, send default one
                log.debug(String.format("no background picture found for user %s, redirecting to default image", user.getUsername()));
                response.sendRedirect(getServletContext().getContextPath() + "/img/default-bg.jpg");
                return;
            }
        } else {
            log.warn("cannot fetch image because of unknown type");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "unknown user image type");
            return;
        }


        response.setContentType("image/png");
        response.getOutputStream().write(image);
        response.getOutputStream().flush();
    }

    /**
     * Sets user image of currently logged-in user
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

        // get type from request (is "profile", if parameter was not specified)
        String type = request.getParameter("type");
        type = type == null ? "profile" : type;

        // read image as byte array from request
        Part imagePart = request.getPart("profile");
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
            UserRepository.setProfileImage(image, user);
        } else if (type.equalsIgnoreCase("background")) {
            UserRepository.setBackgroundImage(image, user);
        } else {
            log.warn(String.format("user %s tried to upload data with unknown type '%s'", user.getUsername(), type));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "user image type unknown");
            return;
        }
        response.sendRedirect(getServletContext().getContextPath()+"/profile?username="+user.getUsername());
        log.info("user uploaded new "+type+" image");
    }
}
