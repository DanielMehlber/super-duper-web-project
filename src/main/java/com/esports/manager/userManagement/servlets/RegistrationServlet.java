package com.esports.manager.userManagement.servlets;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.beans.RegistrationViewBean;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
import com.esports.manager.userManagement.exceptions.UsernameAlreadyTakenException;
import com.esports.manager.userManagement.logic.UserManagement;
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
        // TODO: Block doGet since we won't allow any form actions to do get calls
        doPost(req, resp);
    }

    @java.lang.Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // session bean for view generation
        RegistrationViewBean registrationViewSessionBean = new RegistrationViewBean();

        try {
            UserManagement.registerUser(
                    req.getParameter("username"),
                    req.getParameter("password"),
                    req.getParameter("email"));
        } catch(UsernameAlreadyTakenException ex) {
            // error messages
            log.warn("cannot perform user registration because username is already taken: " + ex.getMessage());
        	registrationViewSessionBean.setErrorMessage("username already in use");
            // place bean in request scope (forwarding)
            req.setAttribute("registrationBean", registrationViewSessionBean);
            // forward back to registration jsp
        	RequestDispatcher rd = req.getRequestDispatcher("/jsp/registration.jsp");
        	rd.forward(req, resp);
        } catch (InvalidInputException ex) {
            // error messages
        	log.warn(String.format("cannot perform user registration because of an invalid user input: %s", ex.getMessage()), ex);
            registrationViewSessionBean.setErrorMessage("your input was not valid");
            // place bean in request scope (forwarding)
            req.setAttribute("registrationBean", registrationViewSessionBean);
            // forward back to registration jsp
            RequestDispatcher rd = req.getRequestDispatcher("/jsp/registration.jsp");
            rd.forward(req, resp);
        } catch (InternalErrorException ex) {
        	log.fatal(String.format("cannot perform user registration because of an internal error: %s", ex.getMessage()), ex);
            // throw servlet exception and redirect to error page
        	throw ex;
        }

        // redirect to JSP
        // TODO: Add a useful file to where we want to redirect
        resp.sendRedirect("www.facebook.com");
    }
}
