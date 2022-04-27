package com.esports.manager.userManagement.servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registrationservlet")
public class RegistrationServlet extends HttpServlet {
    @java.lang.Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: Block doGet since we won't allow any form actions to do get calls
        super.doGet(req, resp);
    }

    @java.lang.Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");

        // forward to JSP
        // TODO: Add link to jsp
        final RequestDispatcher dispatcher = req.getRequestDispatcher("");
        // TODO: Redirect instead of forwarding to prevent from "accidental" reloading
        dispatcher.forward(req, resp);
    }
}
