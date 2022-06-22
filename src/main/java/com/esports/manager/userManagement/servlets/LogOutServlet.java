package com.esports.manager.userManagement.servlets;

import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.beans.LoginViewBean;
import com.esports.manager.userManagement.beans.UserSessionBean;
import com.esports.manager.userManagement.entities.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.rmi.ServerException;

/**
 * This servlet performs the logout for authenticated users
 *
 * @author Philipp Phan
 */

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException, ServletException{
        response.setContentType("text/html;charset=UTF-8");

        //Get current HttpSession
        HttpSession session = request.getSession();

        //Get current user from session
        User user = UserManagement.getAuthorizedUser(request.getSession());
        //Get userSessionBean
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        //Remove sessionBean from session
        //session.invalidate();
        session.removeAttribute("userSessionBean");

        // Forward back to login page
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
        rd.forward(request, response);
    }

}