package com.esports.manager.userManagement.servlets;


import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.beans.RegistrationViewBean;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import com.esports.manager.userManagement.exceptions.UsernameAlreadyTakenException;
import com.esports.manager.userManagement.exceptions.WrongCredentialsException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * We receive data from the registration form, put it in a bean and send it away.
 * Later we redirect the response, since we won't allow any double push problems.
 *
 * @author Maximilian Rublik
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

	private final Logger log = LogManager.getLogger(RegistrationServlet.class);
	
    @java.lang.Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @java.lang.Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // session bean for view generation
        RegistrationViewBean registrationViewSessionBean = new RegistrationViewBean();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Boolean isAdmin = false;

        try {
            UserManagement.registerUser(
                    username,
                    password,
                    email,
                    false);
        } catch(UsernameAlreadyTakenException ex) {
            // error messages
            log.warn("cannot perform user registration because username is already taken: " + ex.getMessage());
        	registrationViewSessionBean.setErrorMessage("username already in use");
            // place bean in request scope (forwarding)
            req.setAttribute("registrationBean", registrationViewSessionBean);
            // forward back to registration jsp
        	RequestDispatcher rd = req.getRequestDispatcher("/jsp/registration.jsp");
        	rd.forward(req, resp);
            return;
        } catch (InvalidInputException ex) {
            // error messages
        	log.warn(String.format("cannot perform user registration because of an invalid user input: %s", ex.getMessage()), ex);
            registrationViewSessionBean.setErrorMessage("your input was not valid");
            // place bean in request scope (forwarding)
            req.setAttribute("registrationBean", registrationViewSessionBean);
            // forward back to registration jsp
            RequestDispatcher rd = req.getRequestDispatcher("/jsp/registration.jsp");
            rd.forward(req, resp);
            return;
        } catch (InternalErrorException ex) {
        	log.fatal(String.format("cannot perform user registration because of an internal error: %s", ex.getMessage()), ex);
            // throw servlet exception and redirect to error page
        	throw ex;
        }

        // perform login with created user
        try {
            UserManagement.performLogin(username, password, req.getSession());
        } catch (NoSuchUserException | WrongCredentialsException e) {
            log.fatal("cannot login user in registration process because of unexpected internal error: " + e.getMessage(), e);
            throw new InternalErrorException("cannot login user in registration process", e);
        }

        // redirect JSP
        resp.sendRedirect(getServletContext().getContextPath() + "/dashboard");
    }
}
