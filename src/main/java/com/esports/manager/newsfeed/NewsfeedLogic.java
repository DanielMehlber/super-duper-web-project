package com.esports.manager.newsfeed;

import com.esports.manager.global.exceptions.InternalErrorException;
import com.esports.manager.newsfeed.db.NewsfeedRepository;
import com.esports.manager.newsfeed.entities.NewsfeedItem;
import com.esports.manager.newsfeed.factory.NewsfeedFactory;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.userManagement.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Contains all newsfeed logic
 * @author Daniel Mehlber
 */
public class NewsfeedLogic {

    private static final Logger log = LogManager.getLogger(NewsfeedLogic.class);

    /**
     * Fetch a specific amount of newsfeed items before the passed date.
     * @param before fetched items are until that date (exclusive)
     * @param count max amount of items that will be fetched
     * @return list of newsfeed items
     * @throws InternalErrorException database error; SQL error
     * @author Daniel Mehlber
     */
    public static List<NewsfeedItem> fetchAmountBefore(final Date before, final int count) throws InternalErrorException {
        log.debug(String.format("%d newsfeed items are requested before %s", count, before.toString()));
        return NewsfeedRepository.fetchNewsfeedItems(before, count);
    }

    /**
     * Create newsfeed for newly registered users
     * @param user user that joined the platform
     * @author Daniel Mehlber
     */
    public static void registerUserRegistration(final User user) throws InternalErrorException {
        // create newsfeed item
        NewsfeedItem item = NewsfeedFactory.newPlayerJoinNews(new Date(), user);
        // persist newsfeed item
        NewsfeedRepository.persistNewsfeedItem(item);
    }

    /**
     * Creates newsfeed item for newly created teams
     * @param team team that was created
     * @throws InternalErrorException database error
     * @author Daniel Mehlber
     */
    public static void registerTeamCreation(final Team team) throws InternalErrorException {
        NewsfeedItem item = NewsfeedFactory.newTeamCreatedNews(new Date(), team);
        NewsfeedRepository.persistNewsfeedItem(item);
    }

    /**
     * Creates newsfeed item when a user joined a team
     * @param team team the user joined to
     * @param member member that joined the team
     * @throws InternalErrorException database error
     * @author Daniel Mehlber
     */
    public static void registerNewMemberOfTeam(final Team team, final User member) throws InternalErrorException {
        NewsfeedItem item = NewsfeedFactory.memberJoinedTeam(new Date(), team, member);
        NewsfeedRepository.persistNewsfeedItem(item);
    }

}
