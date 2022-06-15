package com.esports.manager.games.db;

import com.esports.manager.games.entities.Game;
import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.userManagement.exceptions.NoImageFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Fetches and Updates images of games
 * @author Daniel Mehlber
 */
public class GameImageRepository {

    private static final Logger log = LogManager.getLogger(GameImageRepository.class);

    /**
     * Loads profile image of game
     * @param game game which is owner of the image
     * @return bytes of image
     * @throws InternalErrorException cannot fetch from database; connection error; sql error
     * @throws NoImageFoundException there is no profile picture for this game
     * @author Daniel Mehlber
     */
    public static byte[] loadProfileImage(final Game game) throws InternalErrorException, NoImageFoundException {
        log.debug("loading profile image of game");

        byte[] image;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/games/fetchProfileImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setLong(1, game.getId());
            ResultSet result = pstmt.executeQuery();

            // check if a image for this username has been found
            if(result.next()) {
                image = result.getBytes(1);
            } else {
                log.warn("no profile picture found for game");
                throw new NoImageFoundException(game, "profile");
            }
        } catch (IOException | SQLException e) {
            log.error("cannot fetch profile picture of game because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch profile image of game", e);
        } catch (RuntimeException e) {
            log.error("cannot fetch profile picture of game because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch profile image of game", e);
        }

        // TODO: return value based (not exception based): just return null and check for it.
        if(image == null) {
            log.warn(String.format("no profile image found for game id:%d", game.getId()));
            throw new NoImageFoundException(game, "profile");
        }

        return image;
    }

    /**
     * Loads background image of game with username
     * @param game the game of requested image
     * @return bytes of image
     * @throws InternalErrorException cannot fetch from database; connection error; sql error
     * @throws NoImageFoundException there is no background picture for this game
     * @author Daniel Mehlber
     */
    public static byte[] loadBackgroundImage(final Game game) throws InternalErrorException, NoImageFoundException {
        log.debug("loading background image of game");

        byte[] image;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/games/fetchBackgroundImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setLong(1, game.getId());
            ResultSet result = pstmt.executeQuery();

            // check if a image for this username has been found
            if(result.next()) {
                image = result.getBytes(1);
            } else {
                log.warn("no background picture found for username");
                throw new NoImageFoundException(game, "background");
            }
        } catch (IOException | SQLException e) {
            log.error("cannot fetch background picture of game because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch background image of game", e);
        } catch (RuntimeException e) {
            log.error("cannot fetch background picture of game because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch background image of game", e);
        }

        // TODO: return value based (not exception based): just return null and check for it.
        if(image == null) {
            log.warn(String.format("game id:%d has no background image", game.getId()));
            throw new NoImageFoundException(game, "background");
        }

        return image;
    }

    /**
     * Sets background image of game
     * @param image image data as byte array
     * @param game game the image will be assigned to
     * @throws InternalErrorException cannot write to database
     * @author Daniel Mehlber
     */
    public static void setBackgroundImage(final byte[] image, Game game) throws InternalErrorException {
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/game/setBackgroundImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setBytes(1, image);
            pstmt.setLong(2, game.getId());
            pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            log.error("cannot set background picture of game because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot set background image of game", e);
        } catch (RuntimeException e) {
            log.error("cannot set background picture of game because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot set background image of game", e);
        }
    }

    /**
     * Sets profile image of game
     * @param image image data as byte array
     * @param game image will be assigned to game
     * @throws InternalErrorException cannot write to database
     * @author Daniel Mehlber
     */
    public static void setProfileImage(final byte[] image, Game game) throws InternalErrorException {
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/game/setProfileImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setBytes(1, image);
            pstmt.setLong(2, game.getId());
            pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            log.error("cannot set profile picture of game page because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot set profile image of game", e);
        } catch (RuntimeException e) {
            log.error("cannot set profile picture of game page because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot set profile image of game", e);
        }
    }

}
