package com.esports.manager.userManagement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;

import javax.sql.rowset.serial.SerialException;

import com.esports.manager.userManagement.beans.LoginData;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 *Login Servlet
 * 
 * @author Philipp Phan
 */

public class LoginServlet extends HttpServlet{
   @Override 
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServerException, IOException{
    final String username = request.getParameter("username");
    final String password = request.getParameter("password");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
        /*Compares Username and passwort of user (Should this be implemented somewhere else?)
        Needs fetchUser() to retrieve user from Database
        */
        //User username = getByUsername(username);
        //String userpassword = username.getPassword();
            RequestDispatcher rd = request.getRequestDispatcher(path);
            //Bean creation
            LoginData form = new LoginData();
            form.setUsername(request.getParameter("username"));
            form.setPassword(request.getParameter("password"));
            rd.forward(request, response);
   }
}
