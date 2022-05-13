package com.esports.manager.userManagement.servlets;

import com.esports.manager.userManagement.exceptions.UnauthorizedException;
import com.esports.manager.userManagement.logic.UserManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * We receive a request of a user to view a profile of a user (other user or only himself?)
 *
 * @author Philipp
 */


public class ProfileServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();

        //Check if user is logged in
        boolean loggedIn = true;
        try {
            UserManagement.getAuthorizedUser(currentSession);
        } catch (UnauthorizedException e) {
            // there is no authorized user
            loggedIn = false;
        }
        // redirect accordingly
        if (loggedIn) {
            // redirect to dashboard
            response.sendRedirect("/profile (USER ID) .jsp");
        } else {
            // redirect to login
            response.sendRedirect("/jsp/login.jsp");
        }

    }

}
