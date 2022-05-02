package com.esports.manager.userManagement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpConnectTimeoutException;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;

import javax.sql.rowset.serial.SerialException;

import com.esports.manager.userManagement.beans.LoginData;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.logic.UserManagement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*TODO
 *Login Servlet
 * 
 * @author Philipp Phan
 */
public class LoginServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServerException, IOException {
    final String username = request.getParameter("username");
    final String password = request.getParameter("password");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    /*
     * Get username and password
     *
     */
    RequestDispatcher rd = request.getRequestDispatcher("path");
    /*
     * //Bean creation
     * LoginData form = new LoginData();
     * form.setUsername(request.getParameter("username"));
     * form.setPassword(request.getParameter("password"));
     */
    // Forward username and passwortd to usermanagement
    UserManagement.performLogin(username, password);
    try {
      rd.forward(request, response);
    } catch (ServletException e) {
      e.getCause();
    }

  }
}
