package com.esports.manager.userManagement.servlets;


import com.esports.manager.userManagement.beans.UserSessionBean;
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

        // check if there is a logged-in user in session
        final UserSessionBean userSessionBean = (UserSessionBean) currentSession.getAttribute("loginSessionBean");

        if(userSessionBean == null || userSessionBean.getUser() == null) {
            // case: no user is logged in. Login/Registration options have to be presented
            request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);
        } else {
            // case: a user is already logged in. User can be forwarded to dashboard
            // TODO: forward to dashboard
        }

    }
}
