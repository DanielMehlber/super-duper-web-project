package com.esports.manager.teams.logic;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.teams.exceptions.NoSuchTeamException;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;

import com.esports.manager.userManagement.exceptions.InvalidInputException;
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
       log.debug("fetching all teams...");
        List<Team> teams = TeamRepository.getAllTeams();
        return teams;
    }

    public static List<Member> fetchMembersByTeamId(final long id) throws InternalErrorException {
        log.debug("fetch members by teamId...");
        List<Member> members = TeamRepository.getMemberByTeamId(id);
        return members;
    }

    /**
     * Fetch single team by id
     * @param id
     * @return a single team
     * @throws InternalErrorException some unexpected and fatal internal error occurred
     *
     * @author Maximilian Rublik
     */
    public static Team fetchTeamById(long id) throws NoSuchTeamException, InternalErrorException {
        log.debug("fetch single team by id");
        Team team = TeamRepository.getTeamById(id);
        return team;
    }

    public static void createTeam (String name, String slogan, String tags, byte[] profile) throws InvalidInputException, InternalErrorException {
        Team newTeam = new Team(name, slogan, "");
        TeamRepository.createTeam(newTeam);
    }
}
