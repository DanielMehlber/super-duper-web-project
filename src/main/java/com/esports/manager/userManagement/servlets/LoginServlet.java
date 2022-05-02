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

/*TODO
 *Login Servlet
 * 
 * @author Philipp Phan
 */
@WebServlet
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

    try {
      UserManagement.performLogin(username, password);
    } catch (InternalErrorException e) {
      System.out.println("Internal error found: " + e.getMessage()); 
    } catch (NoSuchUserException e) {
      System.out.println("No user with this username found: " + e.getMessage());
    }

    //Create new Session
    HttpSession session = request.getSession();

    try {
      rd.forward(request, response);
    } catch (ServletException e) {
      System.out.println("Exception in Servlet found: " + e.getMessage());
    }
  }
}
