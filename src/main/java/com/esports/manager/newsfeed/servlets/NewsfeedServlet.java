package com.esports.manager.newsfeed.servlets;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.newsfeed.NewsfeedLogic;
import com.esports.manager.newsfeed.entities.NewsfeedItem;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This servlet returns JSON values of requested newsfeed items for the website to render.
 * @author Daniel Mehlber
 */
@WebServlet("/newsfeed/fetch")
public class NewsfeedServlet extends HttpServlet {

    private final Logger log = LogManager.getLogger(NewsfeedServlet.class);

    /**
     * <h1>Actions</h1>
     * Fetches newsfeed items of a certain amount before a given date/time and returns them as JSON.
     * This is because the newsfeed items should be paged: the UI has the function to load more newsfeed items after the
     * last one has been reached in list.
     * <h1>HTTP Parameters</h1>
     * <ul>
     *     <li><b>before</b> marks the date before the requested news should have happened. If the user wants to load
     *     more news than currently are displayed, the <b>before</b> parameter carries the date/time of the last loaded newsfeed item.
     *     Only news which happened before that last newsfeed item are loaded. If this parameter is not provided, it will be set to the
     *     current date/time, so that the latest news will be loaded</li>
     *     <li><b>amount</b> is the max amount of newsfeed items that are requested.</li>
     * </ul>
     *
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check if user is authorized
        User user = UserManagement.getAuthorizedUser(req.getSession());

        final String beforeDateParameter = req.getParameter("before");
        final String amountParameter = req.getParameter("amount");

        /*
         * if "before" parameter was passed, then convert to date. If not, the default value is the current date.
         */
        final Date beforeDate;
        if(beforeDateParameter == null || beforeDateParameter.isBlank()) {
            // all before now (latest)
            beforeDate = new Date();
        } else {
            // convert passed date string to date
            try {
                beforeDate = new SimpleDateFormat("yyyy-MM-ddTHH:mm:SS").parse(beforeDateParameter);
            } catch (ParseException e) {
                log.warn(String.format("cannot fetch newsfeed: user '%s' passed date with invalid format '%s'",
                        user.getUsername(), beforeDateParameter));
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("date format of 'before' parameter not correct");
                return;
            }
        }

        /*
         * make sure the amount of items is set and extract it
         */
        if(amountParameter == null || amountParameter.isBlank()) {
            log.warn("cannot fetch newsfeed: amount parameter was not set");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("parameter 'amount' is missing");
            return;
        }

        int amount = 0;
        try {
            amount = Integer.valueOf(amountParameter);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("parameter 'amount' must be a number");
            return;
        }

        /*
         * fetch newsfeed items
         */
        List<NewsfeedItem> newsfeedItems;
        try {
            newsfeedItems = NewsfeedLogic.fetchAmountBefore(beforeDate, amount);
        } catch (InternalErrorException e) {
            log.fatal(String.format("cannot fetch newsfeed items: an internal error occurred: %s", e.getMessage()), e);
            throw e;
        }

        /*
         * convert to JSON
         */
        StringBuilder jsonBuilder = new StringBuilder("[");
        for(int i = 0; i < newsfeedItems.size(); i++) {
            String separator = i+1 == newsfeedItems.size() ? "" : ",";
            jsonBuilder.append(newsfeedItems.get(i).toJson()).append(separator);
        }
        jsonBuilder.append("]");

        /*
         * send to client
         */
        resp.getWriter().println(jsonBuilder);
    }
}
