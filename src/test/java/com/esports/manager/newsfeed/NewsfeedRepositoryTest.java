package com.esports.manager.newsfeed;

import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.newsfeed.db.NewsfeedRepository;
import com.esports.manager.newsfeed.entities.NewsfeedItem;
import com.esports.manager.teams.TeamManagement;
import com.esports.manager.teams.entities.Team;
import com.esports.manager.userManagement.UserManagement;
import com.esports.manager.util.DataSourceCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Date;

public class NewsfeedRepositoryTest {

    @BeforeAll
    public static void injectDataSource() {
        // create datasource and inject it into the QueryHandler
        DataSource dataSource = DataSourceCreator.createNewDataSource();
        QueryHandler.setGlobalDataSource(dataSource);
    }

    @BeforeEach
    public void prepare() throws Exception {
        // clear table of newsfeed
        PreparedStatement statement = QueryHandler.loadStatement("/sql/newsfeed/delete-newsfeed.sql");
        statement.executeUpdate();
        statement.close();

        // clear table of users
        statement = QueryHandler.loadStatement("/sql/user-management/delete-all-users.sql");
        statement.executeUpdate();
        statement.close();
    }

    @Test
    public void persistAndLoadNewsfeedItem() throws Exception {
        // -- arrange --
        // create users to refer to in newsfeed item (necessary for foreign key constraint)
        UserManagement.registerUser("player1", "password", "player1@gmail.com", false);
        UserManagement.registerUser("player2", "password", "player2@gmail.com", false);
        Team team1 = TeamManagement.createTeam("team1", "slogan", "abc");
        Team team2 = TeamManagement.createTeam("team2", "slogan", "efg");

        // -- act --
        NewsfeedItem item = new NewsfeedItem(new Date(), "type", "player1", "player2", team1.getId(), team2.getId());
        NewsfeedRepository.persistNewsfeedItem(item);

        Thread.sleep(1000);

        // -- assert --
        NewsfeedItem fetchedItem = NewsfeedRepository.fetchNewsfeedItems(new Date(), 1).get(0);
        Assertions.assertEquals(item.getDate(), fetchedItem.getDate());
        Assertions.assertEquals(item.getPlayer1Id(), fetchedItem.getPlayer1Id());
        Assertions.assertEquals(item.getPlayer2Id(), fetchedItem.getPlayer2Id());
        Assertions.assertEquals(item.getTeam1Id(), fetchedItem.getTeam1Id());
        Assertions.assertEquals(item.getTeam2Id(), fetchedItem.getTeam2Id());
        Assertions.assertEquals(item.getType(), fetchedItem.getType());
    }

}
