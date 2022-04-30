package com.esports.manager.userManagement.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.esports.manager.userManagement.beans.RegistrationBean;

/**
 * We receive data from the registration form, put it in a bean and send it away.
 * Later we redirect the response, since we won't allow any double push problems.
 *
 * @author Maximilian Rublik
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @java.lang.Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: Block doGet since we won't allow any form actions to do get calls
        super.doGet(req, resp);
    }

    @java.lang.Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Bean-Creation
        RegistrationBean form = new RegistrationBean();
        form.setUsername(req.getParameter("username"));
        form.setPassword(req.getParameter("password"));
        form.setEmail(req.getParameter("email"));

        // TODO: Send bean to user management class

        // redirect to JSP
        // TODO: Add file to where we want to redirect
        resp.sendRedirect("");
    }
}
