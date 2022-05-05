package com.esports.manager.userManagement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpConnectTimeoutException;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;

import javax.sql.rowset.serial.SerialException;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.beans.LoginData;
import com.esports.manager.userManagement.entities.User;
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

/*TODO
 *Login Servlet
 * 
 * @author Philipp Phan
 */
@WebServlet
public class LoginServlet extends HttpServlet {
  private static Logger log = LogManager.getLogger(LoginServlet.class);
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServerException, IOException {
    final String username = request.getParameter("username");
    final String password = request.getParameter("password");
    final String email = request.getParameter("email");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    RequestDispatcher rd = request.getRequestDispatcher("path");
    try {
      UserManagement.performLogin(username, password);
    } catch (InternalErrorException e) {
      e.getMessage();
      log.fatal("Internal error found");
      response.getStatus();
    } catch (NoSuchUserException e) {
      log.warn("No user with name:" + username + " found");
    }
    //Create new Session
    HttpSession session = request.getSession();
    //Store user information inside session object
    session.setAttribute("username", username);
    session.setAttribute("password", password);
    session.setAttribute("emailID", email);
    try {
      rd.forward(request, response);
    } catch (ServletException e) {
      log.fatal("Servlet exception found");
      e.getMessage();
    }
  }
}
