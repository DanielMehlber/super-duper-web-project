package com.esports.manager.games.db;

import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.NoSuchGameException;
import com.esports.manager.global.db.mapping.ResultSetProcessor;
import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Database interface for game entities.
 * @author Daniel Mehlber
 */
public class GamesRepository {

    private static final Logger log = LogManager.getLogger(GamesRepository.class);

    /**
     * fetches game with id from database
     * @param id id of game
     * @return game with id
     * @throws NoSuchGameException game with id does not exist
     * @throws InternalErrorException io error; database error; sql error
     * @author Daniel Mehlber
     */
    public static Game fetchById(final long id) throws NoSuchGameException, InternalErrorException {
        log.debug(String.format("loading game with id:%d from database", id));
        List<Game> gameList = new LinkedList<>();
        try(PreparedStatement statement = QueryHandler.loadStatement("/sql/games/fetch-by-id.sql");
            Connection connection = statement.getConnection()) {

            // set parameters
            statement.setLong(1, id);

            // convert result
            ResultSet resultSet = statement.executeQuery();
            gameList = ResultSetProcessor.convert(Game.class, resultSet);

        } catch (SQLException | IOException | RuntimeException | InternalErrorException e) {
            log.error(String.format("cannot fetch game with id:%d due to an internal error: %s", id, e.getMessage()), e);
            throw new InternalErrorException("cannot fetch game", e);
        }

        if(gameList.isEmpty()) {
            log.warn(String.format("cannot fetch game with id:%d: game with id does not exist", id));
            throw new NoSuchGameException(id);
        }

        log.info("fetched game from database");
        return gameList.get(0);
    }

    /**
     * updates game in database
     * @param game game entity with new data and existing id
     * @throws InternalErrorException sql error; database error, io error
     * @author Daniel Mehlber
     */
    public static void update(final Game game) throws InternalErrorException {
        log.debug(String.format("updating game with id:%d", game.getId()));

        try(PreparedStatement statement = QueryHandler.loadStatement("/sql/games/update.sql");
            Connection connection = statement.getConnection()) {

            // set parameters
            statement.setString(1, game.getName());
            statement.setString(2, game.getDescription());
            statement.setLong(3, game.getId());

            // execute update
            statement.executeUpdate();

        } catch (SQLException | IOException | RuntimeException | InternalErrorException e) {
            log.error(String.format("cannot update game with id:%d due to an internal error: %s", game.getId(), e.getMessage()), e);
            throw new InternalErrorException("cannot update game", e);
        }

        log.info("successfully updated game");
    }

    /**
     * insert game in database
     * @param game game entity with new data; will be assigned id
     * @throws InternalErrorException sql error; database error, io error
     * @author Daniel Mehlber
     */
    public static void create(final Game game) throws InternalErrorException {
        log.debug("creating game in database");

        try(PreparedStatement statement = QueryHandler.loadStatement("/sql/games/insert.sql");
            Connection connection = statement.getConnection()) {

            // set parameters
            statement.setString(1, game.getName());
            statement.setString(2, game.getDescription());

            // execute update
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            game.setId(keys.getLong(1));

        } catch (SQLException | IOException | RuntimeException | InternalErrorException e) {
            log.error(String.format("cannot create game due to an internal error: %s", e.getMessage()), e);
            throw new InternalErrorException("cannot create game", e);
        }

        log.info("successfully created game");
    }

    /**
     * delete game from database
     * @param game game entity with existing id
     * @throws InternalErrorException sql error; database error, io error
     * @author Daniel Mehlber
     */
    public static void delete(final Game game) throws InternalErrorException {
        log.debug(String.format("deleting game with id:%d", game.getId()));

        try(PreparedStatement statement = QueryHandler.loadStatement("/sql/games/delete.sql");
            Connection connection = statement.getConnection()) {

            // set parameters
            statement.setLong(1, game.getId());

            // execute update
            statement.executeUpdate();

        } catch (SQLException | IOException | RuntimeException | InternalErrorException e) {
            log.error(String.format("cannot delete game with id:%d due to an internal error: %s", game.getId(), e.getMessage()), e);
            throw new InternalErrorException("cannot delete game", e);
        }

        log.info("successfully deleted game");
    }

    /**
     * searches game in database
     * @param searchTern regex pattern for name or description
     * @return list of games mathing regex pattern
     * @throws InternalErrorException io error; database error; sql error
     * @author Daniel Mehlber
     */
    public static List<Game> search(final String searchTern) throws InternalErrorException {
        log.debug(String.format("searching game by term '%s' in database", searchTern));
        List<Game> gameList = new LinkedList<>();
        try(PreparedStatement statement = QueryHandler.loadStatement("/sql/games/search.sql");
            Connection connection = statement.getConnection()) {

            // set parameters
            statement.setString(1, searchTern);
            statement.setString(2, searchTern);

            // convert result
            ResultSet resultSet = statement.executeQuery();
            gameList = ResultSetProcessor.convert(Game.class, resultSet);

        } catch (SQLException | IOException | RuntimeException | InternalErrorException e) {
            log.error(String.format("cannot search game by term '%s' due to an internal error: %s", searchTern, e.getMessage()), e);
            throw new InternalErrorException("cannot search game", e);
        }


        log.info("fetched games by search from database");
        return gameList;
    }

}
