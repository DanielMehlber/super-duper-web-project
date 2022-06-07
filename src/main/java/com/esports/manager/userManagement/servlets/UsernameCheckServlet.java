package com.esports.manager.userManagement.servlets;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.UserManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * This servlet handles requests for username check by the user UI.
 * @author Daniel Mehlber
 */
@WebServlet("/users/username/check")
public class UsernameCheckServlet extends HttpServlet {

    private final Logger log = LogManager.getLogger(UsernameCheckServlet.class);

    /**
     * Requests the availability of a username. The following parameters are required for this endpoint to work:
     * <ul>
     *     <li><b>username</b> - requested username of user (unchecked in syntax and availability)</li>
     * </ul>
     *
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException an internal error occurred
     * @throws IOException an IO-Error occurred
     *
     * @author Daniel Mehlber
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("username");

        // check if required username parameter was set
        if(username == null) {
            log.warn("cannot check for username availability because no username was specified");
            // send error status code and short message as response
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "no username specified");
        } else {
            try {
                boolean isUsernameAvailable = UserManagement.isUsernameAvailable(username);
                if(isUsernameAvailable) {
                    // send status 200 (=OK)
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().println("username available");
                } else {
                    // send status 409 (=CONFLICT)
                    resp.setStatus(HttpServletResponse.SC_CONFLICT);
                    resp.getWriter().println("username not available");
                }

            } catch (InternalErrorException e) {
                // an internal server error occurred: send status code 500
                log.fatal("cannot check if username if available because of fatal error: ", e);
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "internal server error");
            }
        }
    }
}
