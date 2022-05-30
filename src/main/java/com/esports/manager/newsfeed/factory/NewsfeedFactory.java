package com.esports.manager.newsfeed.factory;

import com.esports.manager.newsfeed.entities.NewsfeedItem;
import com.esports.manager.userManagement.entities.User;

import java.util.Date;

/**
 * Creates various types of newsfeed elements
 * @author Daniel Mehlber
 */
public class NewsfeedFactory {

    public static NewsfeedItem newPlayerJoinNews(final Date date, final User joinedUser) {
        return new NewsfeedItem(date, NewsfeedItem.TYPE_PLAYER_JOIN, joinedUser.getUsername(), null);
    }

}
