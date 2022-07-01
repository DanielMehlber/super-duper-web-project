package com.esports.manager.games.servlets;

import com.esports.manager.games.Games;
import com.esports.manager.games.beans.GamePageViewBean;
import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.NoSuchGameException;
import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * This is the Controller for the Game View page where logged-in users can view or edit information about games on the platform.
 * Only admins can edit games, so the edit-view is restricted.
 *
 * The Controller loads all necessary data of the requested game for the view JSP to display.
 * @author Daniel Mehlber
 */
@WebServlet("/games/game")
public class GamePageServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(GamePageServlet.class);
    private static final String PAGE_MODE_VIEW = "view";
    private static final String PAGE_MODE_EDIT = "edit";
    private static final String HTTP_PARAM_MODE = "mode";
    private static final String HTTP_PARAM_GAME_ID = "id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // authorize access only to logged-in users
        User user = UserManagement.getAuthorizedUser(req.getSession());

        // will be used to check if user is admin or not in the view
        req.setAttribute("user", user);

        /*
         * read parameters and set values accordingly.
         * The parameter 'mode' can take 2 Value: "view" and "edit".
         * The edit mode is reserved for admin user. If there is no parameter specified, assume "view".
         */
        String gameIdParameter = req.getParameter(HTTP_PARAM_GAME_ID);
        String mode = req.getParameter(HTTP_PARAM_MODE); // 'view' or 'edit'
        if(mode == null || mode.isBlank() || (!mode.equals(PAGE_MODE_EDIT) && !mode.equals(PAGE_MODE_VIEW)))
            mode = PAGE_MODE_VIEW;

        // edit page access is restricted to admin user. If the user is not admin, send him to view page.
        if(mode.equals(PAGE_MODE_EDIT) && !user.getIsAdmin()) {
            // user is requesting edit page, but is not an admin: set mode to view
            mode = PAGE_MODE_VIEW;
        }

        if(gameIdParameter == null || gameIdParameter.isBlank()) {
            log.warn("cannot display game because id parameter is not provided");
            resp.sendRedirect(getServletContext().getContextPath() + "/games");
            return;
        }

        long gameId;
        try {
            gameId = Long.parseLong(gameIdParameter);
        } catch (RuntimeException e) {
            log.warn(String.format("cannot display game because id was NaN (id:%s)", gameIdParameter));
            resp.sendRedirect(getServletContext().getContextPath() + "/games");
            return;
        }

        Game requestedGame;
        try {
            requestedGame = Games.fetchById(gameId);
        } catch (NoSuchGameException e) {
            log.warn(String.format("cannot display game because game with id:%d does not exist", gameId));
            resp.sendRedirect(getServletContext().getContextPath() + "/games");
            return;
        }

        // fetch teams playing this game
        List<Team> teamsPlayingGame = Games.getTeamsWithGame(requestedGame);

        // lazy fetch members of team
        for(Team team : teamsPlayingGame) {
            List<Member> members = TeamManagement.fetchMembersByTeamId(team.getId());
            team.setMembers(members);
        }

        /*
         * if there were errors from a previous interaction (e.g. submit of invalid input data) there already is a
         * GamePageViewBean containing the error stored in session. If this is the case, use this bean instead of creating
         * a new one in order to display the error.
         */
        GamePageViewBean gamePageViewBean = (GamePageViewBean) req.getSession().getAttribute("gamePageViewBean");
        req.getSession().removeAttribute("gamePageViewBean");
        if(gamePageViewBean == null) {
            gamePageViewBean = new GamePageViewBean();
        }
        gamePageViewBean.setGame(requestedGame);
        gamePageViewBean.setTeams(teamsPlayingGame);
        req.setAttribute("gamePageViewBean", gamePageViewBean);

        // redirect according to current mode
        if(mode.equals(PAGE_MODE_EDIT)) {
            // navigate to edit page
            req.getRequestDispatcher("/jsp/game-edit.jsp").forward(req, resp);
        } else {
            // navigate to view page
            req.getRequestDispatcher("/jsp/game-view.jsp").forward(req, resp);
        }
    }
}
