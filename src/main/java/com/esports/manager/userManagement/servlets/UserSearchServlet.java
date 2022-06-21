package com.esports.manager.userManagement.servlets;

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
import java.util.ArrayList;
import java.util.List;

/**
 * This servlet searches and returns user data in JSON format
 * @author Daniel Mehlber
 */
@WebServlet("/users/search")
public class UserSearchServlet extends HttpServlet {

    private final Logger log = LogManager.getLogger(UserSearchServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        final User loggedInUser = UserManagement.getAuthorizedUser(req.getSession());

        // read parameters
        final String usernameSearchPattern = req.getParameter("un");

        log.debug(String.format("user %s requested a user search with regex '%s'", loggedInUser.getUsername(), usernameSearchPattern));

        // fetch users
        List<User> users;
        if(usernameSearchPattern == null || usernameSearchPattern.isBlank()) {
            // get all users, no filter has been specified
            log.debug("user search: no pattern was provided, fetching all users");
            users = UserManagement.fetchAllUsers();
        } else {
            // search for user by regex pattern
            users = UserManagement.fetchUserByUsernamePattern(usernameSearchPattern);
        }

        log.debug(String.format("generating json containing %d found users", users.size()));

        // build json
        StringBuilder jsonBuilder = new StringBuilder("[");
        for(int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            List<Long> teamIds = UserManagement.fetchTeamsIdsOfUser(user);
            StringBuilder teamJsonBuilder = new StringBuilder("[");
            for(Long teamId : teamIds) {
                teamJsonBuilder.append(String.format("%d", teamId));

                // if this is not the last element, append comma
                if(!teamIds.get(teamIds.size() - 1).equals(teamId)) {
                    teamJsonBuilder.append(",");
                }
            }
            teamJsonBuilder.append("]");
            String teamsJson = teamJsonBuilder.toString();

            String optionalComma = i+1 == users.size() ? "" : ",";

            // prepare user data
            if(user.getNickname() == null) user.setNickname("¯\\\\_( ͠❛ ͜ʖ ͡❛)_/¯");

            // json of users
            String userJson = String.format("{\"username\":\"%s\",\"email\":\"%s\",\"nickname\":\"%s\", \"teamIds\":%s}%s",
                    user.getUsername(), user.getEmail(), user.getNickname(), teamsJson, optionalComma);
            jsonBuilder.append(userJson);
        }
        jsonBuilder.append("]");

        String json = jsonBuilder.toString();
        resp.getWriter().println(json);
    }
}
