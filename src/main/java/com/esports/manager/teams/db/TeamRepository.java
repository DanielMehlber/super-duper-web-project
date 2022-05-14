package com.esports.manager.teams.db;

import com.esports.manager.global.db.mapping.ResultSetProcessor;
import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.teams.exceptions.NoTeamsFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Database interactions with team entities
 * @author Maximilian Rublik
 */
public class TeamRepository {

    /**
     * fetch all teams from database
     * @return List of teams from database
     * @throws InternalErrorException an unexpected internal error occured
     * @throws NoTeamsFoundException no teams found in database
     * @author Maximilian Rublik
     */
    private static final Logger log = LogManager.getLogger(TeamRepository.class);

    public static List<Team> getAllTeams() throws InternalErrorException, NoTeamsFoundException {
        log.debug("fetching all team entities from database...");

        ResultSet resultSet;
        try {
            PreparedStatement pstmt = QueryHandler.loadStatement("/sql/memberArea/fetchAllTeams.sql");
            resultSet = pstmt.executeQuery();
        } catch (IOException | SQLException e) {
            log.error("cannot fetch teams from database because of an unexpected and fatal error:" + e.getMessage());
            throw new InternalErrorException("cannot fetch teams from database", e);
        }

        log.debug("fetched teams from database");
        List<Team> teams = ResultSetProcessor.convert(Team.class, resultSet);
        if (teams.size() < 1) {
            log.warn("cannot fetch teams from database: no teams found");
            throw new NoTeamsFoundException();
        }

        return teams;
    }

    /**
     * Creates new team to database
     * @param team team data to persist
     * @throws SQLException | IOException an undexpected SQL / IO Exception occured
     * @author Maximilian Rublik
     */
    public static void createTeam(Team team) throws InternalErrorException{
        log.debug("add team to database");

        try {
            PreparedStatement pstmt = QueryHandler.loadStatement("/sql/user-management/createTeam.sql");

            // TODO: set strings accordingly to datasource
            pstmt.setString(1, team.getName());
            pstmt.setString(2, team.getProfilePicture());

            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            log.error("cannot create team to database because of an unexpected sql error" + e.getMessage());
            throw new InternalErrorException("cannot create team", e);
        }

        log.debug("created team to database");
    }
}
