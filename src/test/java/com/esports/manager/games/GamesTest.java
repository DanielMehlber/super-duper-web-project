package com.esports.manager.games;

import com.esports.manager.games.entities.Game;
import com.esports.manager.games.exceptions.NoSuchGameException;
import com.esports.manager.global.db.queries.QueryHandler;
import com.esports.manager.util.DataSourceCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

public class GamesTest {

    @BeforeAll
    public static void injectDataSource() {
        // create datasource and inject it into the QueryHandler
        DataSource dataSource = DataSourceCreator.createNewDataSource();
        QueryHandler.setGlobalDataSource(dataSource);
    }

    @BeforeEach
    public void prepare() throws Exception {
        PreparedStatement statement = QueryHandler.loadStatement("/sql/teams/delete-all-teams.sql");
        statement.executeUpdate();
        statement.getConnection().close();
        statement.close();

        // clear table of newsfeed
        statement = QueryHandler.loadStatement("/sql/newsfeed/delete-newsfeed.sql");
        statement.executeUpdate();
        statement.getConnection().close();
        statement.close();

        // clear table of users
        statement = QueryHandler.loadStatement("/sql/user-management/delete-all-users.sql");
        statement.executeUpdate();
        statement.getConnection().close();
        statement.close();

        statement = QueryHandler.loadStatement("/sql/games/delete-all-games.sql");
        statement.executeUpdate();
        statement.getConnection().close();
        statement.close();
    }

    @Test
    public void createAndFetchGame() throws Exception{
        final String gameName = "gameName";
        final String gameDesc = "gameDesc";
        Game created = Games.createGame(gameName, gameDesc);

        // fetch
        Game fetched = Games.fetchById(created.getId());
        Assertions.assertEquals(created, fetched);
    }

    @Test
    public void updateGame() throws Exception {
        Game initial = Games.createGame("gameName1", "gameDesc1");
        Games.updateGame(initial.getId(), "gameName2", "gameDesc2");

        Game fetched = Games.fetchById(initial.getId());

        Assertions.assertNotEquals(initial, fetched);
        Assertions.assertEquals("gameName2", fetched.getName());
        Assertions.assertEquals("gameDesc2", fetched.getDescription());
    }

    @Test
    public void deleteGame() throws Exception {
        Game initial = Games.createGame("gameName", "gameDescription");
        Games.deleteGame(initial.getId());

        Assertions.assertThrows(NoSuchGameException.class, () -> Games.fetchById(initial.getId()));
    }

    @Test
    public void searchGames() throws Exception {
        Game keyWordInDesc = Games.createGame("name", "lalalkeywordlala");
        Game keyWordInName = Games.createGame("lalalakeywordlala", "description");
        Game noKeyWord = Games.createGame("name", "description");

        List<Game> found = Games.searchGame("keyword");
        if(found.contains(noKeyWord))
            Assertions.fail("result contains game without keyword");
        if(!found.contains(keyWordInDesc))
            Assertions.fail("result does not contain keyWordInDesc");
        if(!found.contains(keyWordInName))
            Assertions.fail("result does not contain keyWordInName");
    }

}
