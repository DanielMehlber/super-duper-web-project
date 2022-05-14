package com.esports.manager.memberArea.logic;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.memberArea.db.TeamRepository;
import com.esports.manager.memberArea.entities.Team;
import com.esports.manager.memberArea.exceptions.NoTeamsFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * collection of methods that will be used in member area
 *
 * @author Maximilian Rublik
 */
public class MemberArea {

    private static final Logger log = LogManager.getLogger(MemberArea.class);

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
}
