package com.esports.manager.userManagement.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.esports.manager.userManagement.beans.RegistrationBean;

/**
 * TODO: Add some stuff to fulfill Daniels deepest whishes and needs
 *
 * @author Maximilian Rublik
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    // TODO: finish lookup for ds
    private DataSource ds;

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

        // DB-Access
        persist(form);

        // redirect to JSP
        // TODO: Add file to where we want to redirect
        resp.sendRedirect("");
    }

    private void persist(RegistrationBean form) throws ServletException {
        // DB-Access
        String[] generatedKeys = new String[] {"ID"};

        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     "INSERT INTO users(email, username, password) VALUES(?,?,?)",
                     generatedKeys)){

            pstmt.setString(1, form.getEmail());
            pstmt.setString(2, form.getUsername());
            pstmt.setString(3, form.getPassword());

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                while(rs.next()) {
                    form.setId(rs.getLong(1));
                }
            }
        }
        catch (Exception ex) {
            throw new ServletException(ex.getMessage());
        }
    }
}
