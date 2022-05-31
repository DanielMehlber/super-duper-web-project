package com.esports.manager.userManagement.servlets;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.beans.ProfileViewBean;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.logic.UserManagement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
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
        User currentUser = UserManagement.getAuthorizedUser(request.getSession());
        User userOfPage = new User();
        ProfileViewBean profileViewBean = new ProfileViewBean();

        // "http://www.leckmichamarsch.de/page?username=daniel&someshit=shit"
        // in JSP: <a href="page?username=${requestScrope.userBean.username}">Hier klicken</a>
        // String username = request.getParameter("username");
        // String someshit = request.getParameter("someshit");

        Boolean editPermission = false;

        // Compare usernames with equals, damit daniel mich nicht haut
        if (currentUser.getUsername().equals(userOfPage.getUsername())){
            editPermission = true;
        }

        try {
            //Get current user from current HttpSession (in order to check if user still exists and to display his data)
            currentUser = UserManagement.fetchUserByUsername(currentUser.getUsername());
            // DONE: pack user entity into bean, not every single attribute
            //Set Attributes of user inside profileViewBean
            profileViewBean.setUser(currentUser);
            profileViewBean.setEditPermission(editPermission);

            // redirect to dashboard
            request.setAttribute("profileViewBean", profileViewBean);
            request.getRequestDispatcher("/jsp/profile.jsp");
        } catch (NoSuchUserException e) {
            log.warn("cannot display profile page: logged in user not found");
            profileViewBean.setErrorMessage("No such user found");
            response.sendRedirect("/dashboard");
        }

        request.setAttribute("viewBean", profileViewBean);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
