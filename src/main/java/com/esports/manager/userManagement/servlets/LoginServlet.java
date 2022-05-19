package com.esports.manager.userManagement.servlets;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.beans.LoginViewBean;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.WrongCredentialsException;
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

import java.io.IOException;
import java.rmi.ServerException;

/**
 * This servlet performs the login for unauthenticated users
 *
 * @author Philipp Phan
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServerException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");


        HttpSession session = request.getSession();
        //Get username and password from JSP -> user performLogin method
        try {
            UserManagement.performLogin(
                    request.getParameter("username"),
                    request.getParameter("password"),
                    session);
            // TODO: redirect accordingly to the logged in dashboard.
            //Forward to welcome page
            response.sendRedirect("www.google.com");
        } catch (NoSuchUserException ex) {
            // User couldn't be found.
            log.warn("Cannot perform login because of wrong username: " + ex.getMessage());
            // create error messages and place them in request
            LoginViewBean viewBean = new LoginViewBean();
            viewBean.setErrorMessage("Username is not correct");
            request.setAttribute("loginBean", viewBean);
            // forward back to login page
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request, response);
        } catch (InternalErrorException e) {
            log.fatal("Internal error found while logging in user: " + e.getMessage());
            throw e;
        } catch (WrongCredentialsException e) {
            // User entered wrong password
            log.warn("login was not successful: " + e.getMessage());
            // Create error messages
            LoginViewBean viewBean = new LoginViewBean();
            viewBean.setErrorMessage("Username or Password was not correct");
            request.setAttribute("loginBean", viewBean);
            // Forward back to login page
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request, response);
        }
    }
}
