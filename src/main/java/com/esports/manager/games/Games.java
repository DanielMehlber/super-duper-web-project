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

    private static final Logger log = LogManager.getLogger(Games.class);

    /**
     * Fetches Game entity by its entity
     * @param id id of game
     * @return game entity (if id exists)
     * @throws NoSuchGameException no such game with passed id in database
     * @throws InternalErrorException database error; sql error; runtime error
     * @author Daniel Mehlber
     */
    public static Game fetchById(long id) throws NoSuchGameException, InternalErrorException {
        return GamesRepository.fetchById(id);
    }

    /**
     * Updates an existing entity of game
     * @param id id of game entity
     * @param name new name of game entity
     * @param description new description of game entity
     * @throws InternalErrorException database error; sql error; runtime error
     * @throws NoSuchGameException cannot update game with passed id because this id does not exist
     * @throws GameDataInsufficientException passed game data is not valid and cannot be accepted
     * @author Daniel Mehlber
     */
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

    /**
     * Delete game entity
     * @param id id of game that will be deleted
     * @throws InternalErrorException database error; sql error; runtime error
     * @throws NoSuchGameException cannot delete game with passed id because id does not exist
     * @author Daniel Mehlber
     */
    public static void deleteGame(final long id) throws InternalErrorException, NoSuchGameException {
        Game toDelete = GamesRepository.fetchById(id);
        GamesRepository.delete(toDelete);
    }

    /**
     * Searches for games by search term
     * @param term search term that will be used (regex)
     * @return list of games matching the search term
     * @throws InternalErrorException database error; sql error; runtime error
     * @author Daniel Mehlber
     */
    public static List<Game> searchGame(final String term) throws InternalErrorException {
        return GamesRepository.search(term);
    }

    /**
     * Creates a new game entity in database
     * @param name name of game
     * @param description description of game
     * @return game entity with id
     * @throws InternalErrorException database error; sql error; runtime error
     * @throws GameDataInsufficientException passed game data cannot be accepted by system
     * @author Daniel Mehlber
     */
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

    /**
     * Adds game to teams game collection
     * @param game game to add to team
     * @param team team that the game will be added to
     * @throws InternalErrorException database error; sql error; runtime error
     * @author Daniel Mehlber
     */
    public static void addToTeam(final Game game, final Team team) throws InternalErrorException {
        GamesRepository.addGameToTeam(game, team);
    }

    /**
     * Lists all teams that have a specific game in their collection
     * @param game the game in collection
     * @return a list of teams playing the passed game
     * @author Daniel Mehlber
     * @throws InternalErrorException database error; sql error; runtime error
     */
    public static List<Team> getTeamsWithGame(final Game game) throws InternalErrorException {
        return GamesRepository.getTeamsOfGame(game);
    }

    /**
     * Removes a game from a teams collection
     * @param team team that will loose the game from its collection
     * @param game game that will be removed form the teams collection
     * @throws InternalErrorException database error; sql error; runtime error
     * @author Daniel Mehlber
     */
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
