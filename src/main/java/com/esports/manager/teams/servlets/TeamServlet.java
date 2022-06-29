package com.esports.manager.teams.servlets;

import com.esports.manager.teams.beans.AddMemberViewBean;
import com.esports.manager.teams.beans.TeamViewBean;
import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author Maximilian Rublik
 */
@WebServlet("/teams/team")
public class TeamServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(TeamsServlet.class);

    /**
     * Loads team by id which is given over the url, loads the team itself as its members
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException an internal error has occurred and will be displayed in an error page
     * @throws IOException an IO error occurred and will be displayed in an error page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedinUser = UserManagement.getAuthorizedUser(req.getSession());
        resp.setContentType("text/html;charset=UTF-8");
        Long id;


        AddMemberViewBean addMemberViewBean = new AddMemberViewBean();

        TeamViewBean teamViewBean = new TeamViewBean();

        try {
            id = Long.parseLong(req.getParameter("id"));
            teamViewBean.setTeam(TeamRepository.getTeamById(id));
            teamViewBean.setMembers(TeamRepository.getMemberByTeamId(id));

            addMemberViewBean.setUsers(UserManagement.fetchUserNotAlreadyMember(id));
            addMemberViewBean.setTeamId(id);

        } catch (NumberFormatException e) {
            log.fatal("there was a problemS casting the id parameter...");
            teamViewBean.setErrorMessage("The given team ID was incorrect..");
        } finally {
            req.setAttribute("teamViewBean", teamViewBean);
            req.setAttribute("addMemberViewBean", addMemberViewBean);

            RequestDispatcher rq = req.getRequestDispatcher("/jsp/team.jsp");
            rq.forward(req, resp);
        }
    }
}
