package com.esports.manager.dashboard.servlets;

import com.esports.manager.dashboard.beans.DashboardBean;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
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
 * Servlet setting up the dashboard in order to display the JSP
 * @author Daniel Mehlber
 */
@WebServlet("/dashboard")
public class DashboardControllerServlet extends HttpServlet {

    private final Logger log = LogManager.getLogger(DashboardControllerServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = UserManagement.getAuthorizedUser(request.getSession());

        // for security fetch user again
        try {
            currentUser = UserManagement.fetchUserByUsername(currentUser.getUsername());
        } catch (NoSuchUserException e) {
            // user does not exist anymore, log out user and redirect him to login page
            log.warn(String.format("user '%s' was logged in, but does not exist anymore. The user was logged out.", currentUser.getUsername()));
            UserManagement.performLogout();
            response.sendRedirect("/");
        }

        DashboardBean dashboardBean = new DashboardBean();
        dashboardBean.setUsername(currentUser.getUsername());

        request.setAttribute("dashboardBean", dashboardBean);
        request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
    }
}
