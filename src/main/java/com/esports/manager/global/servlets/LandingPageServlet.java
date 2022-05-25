package com.esports.manager.global.servlets;

import com.esports.manager.userManagement.exceptions.UnauthorizedException;
import com.esports.manager.userManagement.logic.UserManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * This servlet contains the logic of the landing page. The landing page is meant to check, if a user is
 * already logged in and forward/redirect accordingly.
 *
 * @author Daniel Mehlber
 */
@WebServlet("/index")
public class LandingPageServlet extends HttpServlet {

    /**
     * Checks if a user is already logged in and forwards the user accordingly. If the user is logged in, forward to
     * dashboard. If not, forward to login/registration options.
     * @param request an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException error while performing servlet logic
     * @throws IOException error while performing servlet IO-action
     * @author Daniel Mehlber
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession currentSession = request.getSession();

        // check if user is logged in
        boolean loggedIn = true;
        try {
            UserManagement.getAuthorizedUser(currentSession);
        } catch (UnauthorizedException e) {
            // there is no authorized user
            loggedIn = false;
        }

        // redirect accordingly
        if(loggedIn) {
            // redirect to dashboard
            response.sendRedirect(getServletContext().getContextPath() + "/dashboard");
        } else {
            // redirect to login
            response.sendRedirect(getServletContext().getContextPath() + "/login");
        }

    }
}
