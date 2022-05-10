package com.esports.manager.userManagement.servlets;

import java.io.IOException;
import java.rmi.ServerException;

import com.esports.manager.global.exceptions.InternalErrorException;
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

/**
 * This servlet performs the login for unauthenticated users
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
    try {
		UserManagement.performLogin(
				request.getParameter("username"), 
				request.getParameter("password"), 
				session);
		// TODO: redirect accordingly to the logged in dashboard.
		response.sendRedirect("www.google.com");
	} catch (NoSuchUserException ex) {
		// User couldn't be found.
		// TODO: MAYBE: Do we want to differ between user not found and password wrong?
		request.setAttribute("errorMessage", "Invalid username");
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
	    rd.forward(request, response);
	} catch (InternalErrorException e) {
		// TODO: Do something smart for internal errors, maybe an Exception page or smthing.
		e.printStackTrace();
		response.sendRedirect("www.facebook.com");
	}
  }
}
