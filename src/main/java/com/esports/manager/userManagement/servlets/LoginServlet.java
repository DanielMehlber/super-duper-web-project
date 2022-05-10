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
      throws ServerException, IOException {
    final String username = request.getParameter("username");
    final String password = request.getParameter("password");
    response.setContentType("text/html;charset=UTF-8");

    // Dsipatch to JSP
    // TODO Still needs Path
    RequestDispatcher rd = request.getRequestDispatcher("../jsp/login");
    // Create new Session
    HttpSession session = request.getSession();
    try {
      UserManagement.performLogin(username, password, session);
      response.sendRedirect("../jsp/welcome.jsp");
    } catch (InternalErrorException e) {
      e.getMessage();
      log.error("Internal error found while logging in user");
      response.getStatus();
    } catch (NoSuchUserException e) {
      log.error("No user with name:" + username + " found");
    }

    try {
      rd.forward(request, response);
    } catch (ServletException e) {
      log.fatal("Had difficulties while forwarding");
      e.getMessage();
    }

  }
}
