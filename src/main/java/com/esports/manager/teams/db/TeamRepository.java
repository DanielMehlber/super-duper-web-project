package com.esports.manager.teams.db;

import com.esports.manager.global.db.mapping.ResultSetProcessor;
import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.entities.Member;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;
import com.esports.manager.userManagement.exceptions.NoImageFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Database interactions with team entities
 * @author Maximilian Rublik
 */
public class TeamRepository {
    private static final Logger log = LogManager.getLogger(TeamRepository.class);

    /**
     * fetch all teams from database
     * @return List of teams from database
     * @throws InternalErrorException an unexpected internal error occured
     * @throws NoTeamsFoundException no teams found in database
     * @author Maximilian Rublik
     */
    public static List<Team> getAllTeams() throws InternalErrorException, NoTeamsFoundException {
        log.debug("fetching all team entities from database...");

        List<Team> teams;
        ResultSet resultSet;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/fetchAllTeams.sql");
                Connection connection = pstmt.getConnection()){
            resultSet = pstmt.executeQuery();

            log.debug("fetched teams from database");
            teams = ResultSetProcessor.convert(Team.class, resultSet);
        } catch (IOException | SQLException e) {
            log.error("cannot fetch teams from database because of an unexpected and fatal error:" + e.getMessage());
            throw new InternalErrorException("cannot fetch teams from database", e);
        }

        return teams;
    }

    public static Team getTeamById(final long id) throws InternalErrorException {
        log.debug("fetch single team entity by id from database...");

        List<Team> team;
        ResultSet resultSet;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/fetchTeamById.sql");
                Connection connection = pstmt.getConnection()) {
            pstmt.setLong(1, id);
            resultSet = pstmt.executeQuery();

            // Since we give back a List of results, and we expect only a single one, we take the first one
            team = ResultSetProcessor.convert(Team.class, resultSet);
        } catch (IOException | SQLException  e) {
            log.error("cannot fetch single team by Id from database");
            throw new InternalErrorException("cannot fetch Team by Id from database");
        }

        return team.get(0);
    }

    /**
     * returns the teamleader of the team given by the id
     * @param id teamId
     * @return team leader
     * @author Maximilian Rublik
     */
    public static Member getTeamLeaderByTeamId(final long id) {
        log.debug("fetch team leader by teamId");
        List<Member> teamleader;
        ResultSet results;

        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/fetchTeamLeaderByTeam.sql");
                Connection connection = pstmt.getConnection()) {
            pstmt.setLong(1, id);
            results = pstmt.executeQuery();
            teamleader = ResultSetProcessor.convert(Member.class, results);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } catch (InternalErrorException e) {
            throw new RuntimeException(e);
        }

        return teamleader.get(0);
    }

    public static List<Member> getMemberByTeamId(final long id) throws InternalErrorException {
        log.debug("fetch members by teamId");

        List<Member> members;
        ResultSet results;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/fetchMembersByTeam.sql");
                Connection connection = pstmt.getConnection()){
            pstmt.setLong(1, id);
            results = pstmt.executeQuery();
            members = ResultSetProcessor.convert(Member.class, results);
        } catch (IOException | SQLException e) {
            log.error("cannot fetch members by teamId");
            throw new InternalErrorException("cannot fetch members by teamId from database");
        }

        if (members.size() < 1) {
            log.warn("no members found");

        }

        return members;
    }

    /**
     * Fetch team profile image
     * @param id from team
     * @return profile image from team
     * @throws InternalErrorException some unexpected and fatal internal error occured
     * @throws NoImageFoundException no image found to team
     */
    public static byte [] loadProfileImage(final Long id) throws InternalErrorException, NoImageFoundException {
        log.debug("loading team profile image");

        byte[] image;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/fetchTeamProfileImage.sql");
                Connection connection = pstmt.getConnection()) {
            pstmt.setString(1, id.toString());
            ResultSet result = pstmt.executeQuery();

            // check if an image for this team has been found
            if(result.next()) {
                image = result.getBytes(1);
            } else {
                log.warn("no profile picture found for username");
                throw new NoImageFoundException(id.toString(), "profile");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            log.error("cannot fetch profile picture because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch profile image", e);
        }

        if(image.length == 0) {
            log.warn("team " + id + " has no profile image");
            throw new NoImageFoundException(id.toString(), "profile");
        }

        return image;
    }

    /**
     * Fetch team background image
     * @param id from team
     * @return profile image from team
     * @throws InternalErrorException some unexpected and fatal internal error occured
     * @throws NoImageFoundException no image found to team
     */
    public static byte[] loadBackgroundImage(final Long id) throws InternalErrorException, NoImageFoundException {
        log.debug("loading team background image");

        byte[] image;
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/fetchTeamBackgroundImage.sql");
                Connection connection = pstmt.getConnection()) {
            pstmt.setString(1, id.toString());
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                image = result.getBytes(1);
            } else {
                throw new NoImageFoundException(id.toString(), "background");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new InternalErrorException("cannot fetch background image", e);
        }

        if (image == null) {
            log.warn("team '" + id + "' has no background image");
            throw new NoImageFoundException(id.toString(), "background");
        }

        return image;
    }

    /**
     * Sets profile image of team
     * @param image image data as byte array
     * @param team images will belong to this team
     * @throws InternalErrorException cannot write to database
     * @author Maximilian Rublik
     */
    public static void setProfileImage(final byte[] image, Team team) throws InternalErrorException {
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/setTeamProfileImage.sql");
            Connection connection = pstmt.getConnection()) {
            pstmt.setBytes(1, image);
            pstmt.setLong(2, team.getId());
            pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            log.error("cannot set profile picture for team because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot set team profile image", e);
        } catch (RuntimeException e) {
            log.error("cannot set team profile picture because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot set profile image", e);
        }
    }

    /**
     * Sets background image of team
     * @param image image data as byte array
     * @param team images will belong to this team
     * @throws InternalErrorException cannot write to database
     * @author Maximilian Rublik
     */
    public static void setBackgroundImage(final byte[] image, Team team) throws InternalErrorException {
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/setTeamBackgroundImage.sql");
             Connection connection = pstmt.getConnection()) {
            pstmt.setBytes(1, image);
            pstmt.setLong(2, team.getId());
            pstmt.executeUpdate();
        } catch (IOException | SQLException e) {
            log.error("cannot set team background picture because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot set background image", e);
        } catch (RuntimeException e) {
            log.error("cannot set team background picture because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot set background image", e);
        }
    }

    /**
     * Creates new team to database
     * @param team team data to persist
     * @throws SQLException | IOException an undexpected SQL / IO Exception occured
     * @author Maximilian Rublik
     */
    public static void createTeam (Team team) throws InternalErrorException{
        log.debug("add team to database");

        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/createTeam.sql");
                Connection connection = pstmt.getConnection()){
            pstmt.setString(1, team.getName());
            pstmt.setString(2, team.getSlogan());
            pstmt.setString(3, team.getTags());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()){
                while (rs.next()) {
                    team.setId(rs.getLong(1));
                }
            }
        } catch (SQLException | IOException e) {
            log.error("cannot create team to database because of an unexpected sql error" + e.getMessage());
            throw new InternalErrorException("cannot create team", e);
        }

        log.debug("created team to database");
    }

    public static void addUserToTeam (Long teamid, String username, String role, Date since, boolean isTeamLeader) throws InternalErrorException {
        log.debug("add user to team");

        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/addUserToTeam.sql");
            Connection connection = pstmt.getConnection()) {
            pstmt.setString(1, username);
            pstmt.setLong(2, teamid);
            pstmt.setString(3, role);
            pstmt.setDate(4, since);
            pstmt.setBoolean(5, isTeamLeader);
            pstmt.executeUpdate();

        } catch (SQLException | IOException e) {
            log.error("cannot update team with User due to an sql error");
            throw new InternalErrorException("cannot update Team with new user", e);
        }
    }

    /**
     * Removes member from team
     * @param username username from member to be remmoved
     * @param teamId teamId from which the member shall be removed
     * @author Maximilian Rublik
     */
    public static void removeUserFromTeam (String username, Long teamId) throws InternalErrorException {
        log.debug("remove user from team");

        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/removeUserFromTeam.sql");
            Connection connection = pstmt.getConnection()) {
            pstmt.setString(1, username);
            pstmt.setLong(2, teamId);
            pstmt.executeUpdate();

        } catch (SQLException | IOException e) {
            log.error("cannot remove user from team due to an sql error");
            throw new InternalErrorException("cannot remove member from team", e);
        }
    }

    /**
     * Removes team by teamId
     * @param teamId
     * @author Maximilian Rublik
     */
    public static void removeTeam (Long teamId) {
        log.debug("remove team by Id");

        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/removeTeamByTeamId.sql");
                Connection connection = pstmt.getConnection()) {
            pstmt.setLong(1, teamId);
            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            log.error("cannot remove team by ID due to an sql error");
            throw new RuntimeException(e);
        } catch (InternalErrorException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Team> fetchAllTeamsByNamePattern(String teamSearchPattern) throws InternalErrorException {
        log.debug("loading teams according to search pattern");

        List<Team> teams;
        //TODO: Statement
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/fetchTeamByNamePattern.sql");
             Connection connection = pstmt.getConnection()){
            pstmt.setString(1, teamSearchPattern);
            ResultSet resultSet = pstmt.executeQuery();

            teams = ResultSetProcessor.convert(Team.class, resultSet);
        } catch (IOException | SQLException e) {
            log.error("cannot fetch users with username pattern because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch users with username pattern", e);
        } catch (RuntimeException e) {
            log.error("cannot fetch users with username pattern because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch users with username pattern", e);
        }

        return teams;
    }

    public static List<Team> fetchAllTeamsByFilterAndWithNamePattern(String teamSearchPattern, Long gameId) throws InternalErrorException {
        log.debug("loading teams according to search pattern");

        List<Team> teams;
        //TODO: Statement
        try (PreparedStatement pstmt = QueryHandler.loadStatement("/sql/teams/fetchTeamByFilterAndNamePattern.sql");
             Connection connection = pstmt.getConnection()){
            pstmt.setString(1, teamSearchPattern);
            pstmt.setLong(2, gameId);
            ResultSet resultSet = pstmt.executeQuery();

            teams = ResultSetProcessor.convert(Team.class, resultSet);
        } catch (IOException | SQLException e) {
            log.error("cannot fetch users with username pattern because of an unexpected sql error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch users with username pattern", e);
        } catch (RuntimeException e) {
            log.error("cannot fetch users with username pattern because of an unexpected internal error: " + e.getMessage());
            throw new InternalErrorException("cannot fetch users with username pattern", e);
        }

        return teams;
    }
}
