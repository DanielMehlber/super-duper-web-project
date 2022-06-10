package com.esports.manager.userManagement.servlets;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.servlets.RemoveMemberServlet;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.UnauthorizedException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@WebServlet("users/removeuser")
public class RemoveUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private final Logger log = LogManager.getLogger(RemoveMemberServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws InternalErrorException, UnauthorizedException, IOException {
        User user = UserManagement.getAuthorizedUser(request.getSession());

        if(user != null && user.getIsAdmin()){
            String username = request.getParameter("username");
            UserManagement.removeUser(username);

            response.sendRedirect(getServletContext().getContextPath());
        }

    }
}
