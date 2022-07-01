package com.esports.manager.games.servlets;

import com.esports.manager.games.Games;
import com.esports.manager.games.beans.GamePageViewBean;
import com.esports.manager.games.db.GamesRepository;
import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.GameDataInsufficientException;
import com.esports.manager.games.exceptions.NoSuchGameException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.UnauthorizedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Manages requests for editing or deleting game data coming from the game edit page.
 * This controller has 2 modes: 'edit' (default) and 'delete'.
 * @author Daniel Mehlber
 */
@WebServlet("/games/game/edit")
public class GameEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(GameEditServlet.class);
    public static final String PARAM_GAME_ID = "id";
    public static final String PARAM_MODE = "mode";
    public static final String MODE_EDIT = "edit";
    public static final String PARAM_ITEM = "item";
    public static final String PARAM_VALUE = "value";
    public static final String ITEM_TITLE = "title";
    public static final String ITEM_DESCRIPTION = "description";
    public static final String MODE_DELETE = "delete";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        User currentUser;
        if(!(currentUser = UserManagement.getAuthorizedUser(request.getSession())).getIsAdmin()) {
            log.warn(String.format("user '%s' tried to edit game, which is only possible as admin user. Request denied.", currentUser.getUsername()));
            throw new UnauthorizedException();
        }

        // read parameter values
        String parameterId = request.getParameter(PARAM_GAME_ID);
        if(parameterId == null || parameterId.isBlank()) {
            log.warn("cannot change game data: parameter id not set");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game id is missing");
            return;
        }

        long id;
        try {
            id = Long.parseLong(parameterId);
        } catch (NumberFormatException e) {
            log.warn("cannot set data of game: game id is not valid number");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game id is NaN");
            return;
        }

        Game game;
        try {
            game = GamesRepository.fetchById(id);
        } catch (NoSuchGameException e) {
            log.warn(String.format("cannot set data of game: game with id:%d does not exist", id));
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game with passed id does not exist");
            return;
        }

        /*
         * 2 modes are possible for this request: "edit" and "delete"
         * The default (if no value is specified) is "edit"
         */
        String parameterMode = request.getParameter(PARAM_MODE);
        if(parameterMode == null || parameterMode.isBlank()) {
            parameterMode = MODE_EDIT;
        }

        if(parameterMode.equals(MODE_EDIT)) {
            String parameterItem = request.getParameter(PARAM_ITEM);
            if (parameterItem == null || parameterItem.isBlank()) {
                log.warn("cannot change game data: item parameter not set");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "item parameter is missing");
                return;
            }


            String parameterValue = request.getParameter(PARAM_VALUE);
            if (parameterValue == null || parameterValue.isBlank()) {
                log.warn("cannot set game data: value was not set");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "no value specified");
                return;
            }

            try {
                if (parameterItem.equals(ITEM_TITLE)) {
                    game.setName(parameterValue);
                    Games.updateGame(game.getId(), game.getName(), game.getDescription());
                } else if (parameterItem.equals(ITEM_DESCRIPTION)) {
                    game.setDescription(parameterValue);
                    Games.updateGame(game.getId(), game.getName(), game.getDescription());
                } else {
                    log.warn("cannot set data of game: unknown item value " + parameterItem);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "game item unkown");
                    return;
                }
            } catch (GameDataInsufficientException e) {
                // ISSUE: the passed game data is not valid (e.g. contains invalid characters)
                log.warn(String.format("cannot update game id:%d because passed data was invalid", game.getId()));

                /*
                 * this page will redirect to the game page controller. This controller will look for a session bean
                 * containing errors from a past interaction, that need to be displayed on the game page.
                 *
                 * The error set here will be displayed on the game page
                 */
                GamePageViewBean gameEditPageViewBean = new GamePageViewBean();
                gameEditPageViewBean.setError("Cannot apply received game data: Please make sure to only use valid characters");
                request.getSession().setAttribute("gamePageViewBean", gameEditPageViewBean);

                // redirect to edit page (in order to display error)
                response.sendRedirect(getServletContext().getContextPath() + "/games/game?mode=edit&id=" + game.getId());
                return;
            } catch (NoSuchGameException ignored) {}

            response.sendRedirect(getServletContext().getContextPath() + "/games/game?id=" + game.getId());
            log.info("game data changed");
        } else if (parameterMode.equals(MODE_DELETE)) {
            // delete game
            try {
                Games.deleteGame(game.getId());
            } catch (NoSuchGameException ignored) {}

            log.info(String.format("game with id:%d was deleted by user '%s'", game.getId(), currentUser.getUsername()));

            // redirect to games page
            response.sendRedirect(getServletContext().getContextPath() + "/games");
            return;
        } else {
            // mode parameter not correct, must be either 'edit' or 'delete'
            log.warn(String.format("cannot edit game: invalid mode '%s' was specified", parameterMode));
            response.getWriter().println("invalid mode specified, must be either 'edit' or 'delete'");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

    }
}
