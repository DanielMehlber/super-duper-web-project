package com.esports.manager.userManagement.servlets;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.servlets.RemoveMemberServlet;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.UnauthorizedException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Servlet to remover users from the system
 *
 * @author Philipp Phan
 */

@WebServlet("users/removeUser")
public class RemoveUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private final Logger log = LogManager.getLogger(RemoveUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            //Get authorized User
            User user = UserManagement.getAuthorizedUser(request.getSession());

            //Checks if user exists and user is admin
            if(user != null && user.getIsAdmin()){
            String username = request.getParameter("username");

            //execute removeUser method
            UserManagement.removeUser(username);

            //redirect to /users page
            response.sendRedirect(getServletContext().getContextPath() + "/users");
            }
        } catch (InternalErrorException e) {
            log.error("An internal Error occured: " + e.getMessage());
        }

    }
}
