package com.esports.manager.newsfeed.db;


import com.esports.manager.global.db.mapping.ResultSetProcessor;
import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.newsfeed.entities.NewsfeedItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * Manages all database interactions on newsfeeds
 * @author Daniel Mehlber
 */
public class NewsfeedRepository {

    private final static Logger log = LogManager.getLogger(NewsfeedRepository.class);


    /**
     * Fetches a list of newsfeed items from database
     * @param before fetch news before that date
     * @param amount amount of news that should be fetched
     * @return list of newsfeed items
     * @throws InternalErrorException database error; result set parsing error; runtime error
     * @author Daniel Mehlber
     */
    public static List<NewsfeedItem> fetchNewsfeedItems(final Date before, int amount) throws InternalErrorException {
        List<NewsfeedItem> fetchedNewsfeedItems;
        try(PreparedStatement statement = QueryHandler.loadStatement("/sql/newsfeed/fetch-newsfeed-before.sql");
            Connection connection = statement.getConnection()) {

            // fetch from database
            statement.setTimestamp(1, new Timestamp(before.getTime()));
            statement.setInt(2, amount);
            ResultSet resultSet = statement.executeQuery();

            // convert into objects
            fetchedNewsfeedItems = ResultSetProcessor.convert(NewsfeedItem.class, resultSet);
            log.debug(String.format("fetching newsfeed successful: fetched %d/%d requested items", fetchedNewsfeedItems.size(), amount));
        } catch (SQLException | IOException | InternalErrorException | RuntimeException e) {
            log.error(String.format("cannot fetch newsfeed items: an internal error occurred while fetching from database: %s", e.getMessage()), e);
            throw new InternalErrorException("cannot fetch newsfeed items: an internal error occurred", e);
        }
        return fetchedNewsfeedItems;
    }

    /**
     * Persists a new newsfeed item in database
     * @param item newsfeed item (id will be overwritten)
     * @throws InternalErrorException database error; cannot prepare statement; id generation error; SQL error
     * @author Daniel Mehlber
     */
    public static void persistNewsfeedItem(final NewsfeedItem item) throws InternalErrorException {
        try(PreparedStatement statement = QueryHandler.loadStatement("/sql/newsfeed/insert-newsfeed.sql");
            Connection connection = statement.getConnection()) {

            statement.setTimestamp(1, new Timestamp(item.getDate().getTime()));
            statement.setString(2, item.getPlayer1Id());
            statement.setString(3, item.getPlayer2Id());

            statement.executeUpdate();

            // get id of newsfeed item
            long id;
            ResultSet keyResultSet = statement.getGeneratedKeys();
            if(keyResultSet.next()) {
                id = keyResultSet.getLong(1);
            } else {
                throw new InternalErrorException("cannot retrieve id of generated newsfeed item");
            }

            // set new id in entity
            item.setId(id);
            log.debug(String.format("persisted new newsfeed item with id:%d", id));
        } catch (SQLException | InternalErrorException | IOException e) {
            log.error(String.format("cannot insert newsfeed item: an internal error occurred: %s", e.getMessage()), e);
            throw new InternalErrorException("cannot insert newsfeed item due to an internal error", e);
        }
    }

}
