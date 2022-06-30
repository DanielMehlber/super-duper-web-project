package com.esports.manager.games;

import com.esports.manager.games.db.GamesRepository;
import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.GameDataInsufficientException;
import com.esports.manager.games.exceptions.NoSuchGameException;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.entities.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Implements logic for games management
 * @author Daniel Mehlber
 */
public class Games {

    // TODO: Documentation (Daniel)
    private static final Logger log = LogManager.getLogger(Games.class);

    public static Game fetchById(long id) throws NoSuchGameException, InternalErrorException {
        return GamesRepository.fetchById(id);
    }

    public static void updateGame(final long id, final String name, final String description) throws InternalErrorException, NoSuchGameException, GameDataInsufficientException {
        Game current = GamesRepository.fetchById(id);
        current.setName(name);
        current.setDescription(description);

        if(!isGameDataValid(current)) {
            log.warn("cannot update game: passed game data was not sufficient and cannot be accepted");
            throw new GameDataInsufficientException();
        }

        GamesRepository.update(current);
    }

    public static void deleteGame(final long id) throws InternalErrorException, NoSuchGameException {
        Game toDelete = GamesRepository.fetchById(id);
        GamesRepository.delete(toDelete);
    }

    public static List<Game> searchGame(final String term) throws InternalErrorException {
        return GamesRepository.search(term);
    }

    public static Game createGame(final String name, final String description) throws InternalErrorException, GameDataInsufficientException {
        Game game = new Game();
        game.setName(name);
        game.setDescription(description);

        if(!isGameDataValid(game)) {
            log.warn("cannot create new game: game data is not sufficient");
            throw new GameDataInsufficientException();
        }

        GamesRepository.create(game);
        return game;
    }

    public static List<Game> search(final String term) throws InternalErrorException {
        return GamesRepository.search(term);
    }

    public static void addToTeam(final Game game, final Team team) throws InternalErrorException {
        GamesRepository.addGameToTeam(game, team);
    }

    public static List<Team> getTeamsWithGame(final Game game) throws InternalErrorException {
        return GamesRepository.getTeamsOfGame(game);
    }

    public static void removeGameFromTeam(final Team team, final Game game) throws InternalErrorException {
        GamesRepository.removeGameFromTeam(game, team);
    }

    /**
     * Select a random game from {@link GamesRepository} and return it.
     * @return a random game or null, if there are no games in database
     * @throws InternalErrorException database error; sql error; connection error
     * @author Daniel Mehlber
     */
    public static Game getRandomGame() throws InternalErrorException {
        Game game;
        try {
            game = GamesRepository.getRandomGame();
        } catch (NoSuchGameException e) {
            return null;
        }
        return game;
    }

    /**
     * Get the game played by the team selected by teamId
     *
     * @return game played by teamId
     * @throws InternalErrorException database error; sql error; connection error;
     * @author Maximilian Rublik
     */
    public static Game getGameByTeamId(Long teamId) throws InternalErrorException {
        Game game;
        try {
            game = GamesRepository.fetchGameByTeamId(teamId);
        } catch (NoSuchGameException e) {
            return null;
        }

        return game;
    }

    /**
     * Checks if passed game data is valid
     * @author Daniel Mehlber
     */
    public static boolean isGameDataValid(final Game game) {
        final String multiLineText = "^[-,.\\w\\s:;\\(\\)!?]*$";
        final String singeLineText = "^[-,.\\w :;!?]*$";

        // check game description
        if(!Pattern.matches(multiLineText, game.getDescription())) {
            log.warn("game data is not valid: description contains forbidden characters");
            return false;
        }

        // check game name
        if(!Pattern.matches(singeLineText, game.getName())) {
            log.warn("game data is not valid: name contains forbidden characters");
            return false;
        }

        return true;
    }
}
