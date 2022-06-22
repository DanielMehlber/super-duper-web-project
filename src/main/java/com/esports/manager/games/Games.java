package com.esports.manager.games;

import com.esports.manager.games.db.GamesRepository;
import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.NoSuchGameException;
import com.esports.manager.global.exceptions.InternalErrorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Implements logic for games management
 * @author Daniel Mehlber
 */
public class Games {

    private static final Logger log = LogManager.getLogger(Games.class);

    public static Game fetchById(long id) throws NoSuchGameException, InternalErrorException {
        return GamesRepository.fetchById(id);
    }

    public static void updateGame(final long id, final String name, final String description) throws InternalErrorException, NoSuchGameException {
        Game current = GamesRepository.fetchById(id);
        current.setName(name);
        current.setDescription(description);
        GamesRepository.update(current);
    }

    public static void deleteGame(final long id) throws InternalErrorException, NoSuchGameException {
        Game toDelete = GamesRepository.fetchById(id);
        GamesRepository.delete(toDelete);
    }

    public static List<Game> searchGame(final String term) throws InternalErrorException {
        return GamesRepository.search(term);
    }

    public static Game createGame(final String name, final String description) throws InternalErrorException {
        Game game = new Game();
        game.setName(name);
        game.setDescription(description);
        GamesRepository.create(game);
        return game;
    }

    public static List<Game> search(final String term) throws InternalErrorException {
        return GamesRepository.search(term);
    }

}
