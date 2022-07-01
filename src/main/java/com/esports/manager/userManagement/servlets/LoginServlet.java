package com.esports.manager.userManagement.servlets;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.beans.LoginViewBean;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.WrongCredentialsException;
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

        String enteredUsername = request.getParameter("username");
        LoginViewBean loginViewBean = new LoginViewBean();
        loginViewBean.setUsername(enteredUsername);

        HttpSession session = request.getSession();
        //Get username and password from JSP -> user performLogin method
        try {
            UserManagement.performLogin(
                    enteredUsername,
                    request.getParameter("password"),
                    session);
        } catch (NoSuchUserException ex) {
            // User couldn't be found.
            log.warn("Cannot perform login because of wrong username: " + ex.getMessage());
            // create error messages and place them in request
            loginViewBean.setErrorMessage("Username is not correct");
            request.setAttribute("loginBean", loginViewBean);
            // forward back to login page
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request, response);
            return;
        } catch (InternalErrorException e) {
            log.fatal("Internal error found while logging in user: " + e.getMessage());
            throw e;
        } catch (WrongCredentialsException e) {
            // User entered wrong password
            log.warn("login was not successful: " + e.getMessage());
            // Create error messages
            loginViewBean.setErrorMessage("Username or Password was not correct");
            request.setAttribute("loginBean", loginViewBean);
            // Forward back to login page
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
            rd.forward(request, response);
            return;
        }

        response.sendRedirect(getServletContext().getContextPath() + "/dashboard");
    }
}
