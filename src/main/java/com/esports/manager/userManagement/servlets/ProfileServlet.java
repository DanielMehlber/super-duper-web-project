package com.esports.manager.userManagement.servlets;

import com.esports.manager.userManagement.beans.ProfileViewBean;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.UserManagement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Servlet setting up the profile of the user, in order to display the JSP
 *
 * @author Philipp Phan
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * TODO: this page must show information to every user (logged in self or selected user)
         *  - if user is the logged in user: show edit options
         *  - if user is not the logged in user: do not display edit options
         */
        HttpSession currentSession = request.getSession();


        ProfileViewBean profileViewBean = new ProfileViewBean();

        // "http://www.leckmichamarsch.de/page?username=daniel&someshit=shit"
        // in JSP: <a href="page?username=${requestScrope.userBean.username}">Hier klicken</a>
        // String username = request.getParameter("username");
        // String someshit = request.getParameter("someshit");

        Boolean editPermission = false;

        try {
            // 1) fetch currentUser from session
            User currentUser = UserManagement.getAuthorizedUser(request.getSession());
            // 2) fetch profilePageUser from database with username parameter
            String usernameParameter = request.getParameter("username");
            if(usernameParameter == null || usernameParameter.isBlank()) {
                // there is no username specified, redirect to dashboard
                response.sendRedirect(getServletContext().getContextPath() + "/dashboard");
                return;
            }
            User userOfPage = UserManagement.fetchUserByUsername(usernameParameter);
            // 3) compare both to set permissions
            // Compare usernames with equals, damit daniel mich nicht haut
            if (currentUser.getUsername().equals(userOfPage.getUsername())) {
                editPermission = true;
            }

            // 4) set profilePageUser in profile bean
            profileViewBean.setUser(userOfPage);
            // 5) set permission in profile bean
            profileViewBean.setEditPermission(editPermission);
            request.setAttribute("profileViewBean", profileViewBean);
            RequestDispatcher rq = request.getRequestDispatcher("/jsp/profile.jsp");
            rq.forward(request, response);
        } catch (NoSuchUserException e) {
            log.warn("cannot display profile page: logged in user not found");
            profileViewBean.setErrorMessage("No such user found");
            response.sendRedirect("/dashboard");
            return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
