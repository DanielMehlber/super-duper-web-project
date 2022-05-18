package com.esports.manager.userManagement.servlets;

import com.esports.manager.userManagement.beans.ProfileViewBean;
import com.esports.manager.userManagement.beans.UserSessionBean;
import com.esports.manager.userManagement.db.UserRepository;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.UnauthorizedException;
import com.esports.manager.userManagement.logic.UserManagement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.sql.Blob;

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
        HttpSession currentSession = request.getSession();
        User currentUser = UserManagement.getAuthorizedUser(request.getSession());
        ProfileViewBean profileViewBean = new ProfileViewBean();

        //Check if user is logged in
        boolean loggedIn = true;
        try {
            UserManagement.getAuthorizedUser(currentSession);
        } catch (UnauthorizedException e) {
            // there is no authorized user
            loggedIn = false;
            log.warn("Login to view Profile");
            profileViewBean.setErrorMessage("Unauthorized user");

        }
        // redirect accordingly
        if (loggedIn) {
            try {
                //Get current user from current HttpSession
                currentUser = UserManagement.fetchUserByUsername(currentUser.getUsername());

                //Set Attributes of user inside of profileViewBean
                profileViewBean.setUsername(currentUser.getUsername());
                profileViewBean.setEmail(currentUser.getEmail());
                //profileViewBean.setProfile_picture(UserRepository.loadProfileImage(username));
                //profileViewBean.setBackground_picture(UserRepository.loadBackgroundImage(username));

                // redirect to dashboard
                request.setAttribute("profileViewBean", profileViewBean);
                response.sendRedirect("/profile/username.jsp");
            } catch (NoSuchUserException e) {
                log.warn("No such user found");
                profileViewBean.setErrorMessage("No such user found");
                response.sendRedirect("jsp/login.jsp");
            }

        } else {
            // redirect to login
            response.sendRedirect("/jsp/login.jsp");
        }
        request.setAttribute("viewBean", profileViewBean);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Method to post / change profile picture

        //set user_image
        //UserRepository.setProfileImage()

        //set user_background_image
        //UserRepository.setBackgroundImage()
    }

}
