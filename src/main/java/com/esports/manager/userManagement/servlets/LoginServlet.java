package com.esports.manager.userManagement.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.rmi.server.ServerCloneException;

import javax.sql.rowset.serial.SerialException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        if(password.equals("userpassword") && username.equals("username")){
            RequestDispatcher rd = request.getRequestDispatcher("");
            rd.forward(request, response);
        }
        else{
            out.println("Username or Password incorrect");
            RequestDispatcher rd = request.getRequestDispatcher("../ui/LoginPage.html");
            rd.include(request, response);
        }
   }
}
