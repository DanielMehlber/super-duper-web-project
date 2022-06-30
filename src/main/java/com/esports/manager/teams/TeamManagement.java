package com.esports.manager.teams;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.newsfeed.NewsfeedLogic;
import com.esports.manager.teams.db.TeamRepository;
import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.teams.exceptions.NoSuchTeamException;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.userManagement.entities.User;
import com.esports.manager.userManagement.exceptions.InvalidInputException;
import com.esports.manager.userManagement.exceptions.NoSuchUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

/**
 * collection of methods that will be used in teams
 * @author Maximilian Rublik
 */
public class TeamManagement {

    private static final Logger log = LogManager.getLogger(TeamManagement.class);

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

       return TeamRepository.getAllTeams();
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

    /**
     * Remove team by teamID
     * @param teamId
     *
     * @author Maximilian Rublik
     */
    public static void removeTeamByTeamId(long teamId) {
        TeamRepository.removeTeam(teamId);
    }

    /**
     * @param name
     * @param slogan
     * @param tags
     * @throws InvalidInputException
     * @throws InternalErrorException
     *
     * @author Maximilian Rublik
     */
    public static Team createTeam (String name, String slogan, String tags) throws InvalidInputException, InternalErrorException {
        Team newTeam = new Team(name, slogan, tags);
        TeamRepository.createTeam(newTeam);

        // create entry in newsfeed
        NewsfeedLogic.registerTeamCreation(newTeam);
        return newTeam;
    }

    public static Member fetchTeamLeaderByTeamId(long id) {
        return TeamRepository.getTeamLeaderByTeamId(id);
    }

    /**
     *
     * @param username
     * @param teamid
     * @param role
     * @param since
     * @throws InternalErrorException some unexpected and fatal internal error occurred
     *
     * @author Maximilian Rublik
     */
    public static void addUserToTeam (String username, Long teamid, String role, Date since, boolean isTeamLeader) throws InternalErrorException, NoSuchTeamException, NoSuchUserException {
        // check that referenced entites exist
        User user = UserManagement.fetchUserByUsername(username);
        Team team = TeamManagement.fetchTeamById(teamid);

        TeamRepository.addUserToTeam(teamid, username, role, since, isTeamLeader);
        // add newsfeed item
        NewsfeedLogic.registerNewMemberOfTeam(team, user);
    }

    /**
     * @param username username from member to be removed
     * @param teamId from team where to remove from
     * @author Maximilian Rublik
     */
    public static void removeUserFromTeam (String username, Long teamId) {
        try {
            TeamRepository.removeUserFromTeam(username, teamId);
        } catch (InternalErrorException ex) {
            log.warn("");
        }
    }

    public static List<Team> fetchTeamByFilterAndNamePattern(String teamSearchPattern, Long gameId) throws InternalErrorException, NoTeamsFoundException {
        if (gameId == 0 && (teamSearchPattern == null || teamSearchPattern.isBlank())) {
            return TeamRepository.getAllTeams();
        } else if (gameId == 0) {
            return TeamRepository.fetchAllTeamsByNamePattern(teamSearchPattern);
        }
        return TeamRepository.fetchAllTeamsByFilterAndWithNamePattern(teamSearchPattern, gameId);
    }
}
