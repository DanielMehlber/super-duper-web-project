package com.esports.manager.teams.logic;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * collection of methods that will be used in teams
 *
 * @author Maximilian Rublik
 */
public class Teams {

    private static final Logger log = LogManager.getLogger(Teams.class);

    /**
     * Fetch all teams from database, unordered
     * @return list of all teams
     * @throws NoTeamsFoundException no teams found in database
     * @throws InternalErrorException some unexpected and fatal internal error occurred
     *
     * @author Maximilian Rublik
     */
    public static List<Team> fetchAllTeams() throws NoTeamsFoundException, InternalErrorException {
        // TODO: Add filter directly to this method?

        log.debug("fetching all teams...");
        List<Team> teams = TeamRepository.getAllTeams();
        return teams;
    }

    /**
     * Fetch single team by id
     * @param id
     * @return a single team
     * @throws InternalErrorException some unexpected and fatal internal error occurred
     *
     * @author Maximilian Rublik
     */
    public static Team fetchTeamById(long id) throws InternalErrorException {
        log.debug("fetch single team by id");
        Team team = TeamRepository.getTeamById(id);
        return team;
    }
}
