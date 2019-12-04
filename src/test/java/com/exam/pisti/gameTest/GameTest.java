package com.exam.pisti.gameTest;

import com.exam.pisti.bots.BotFactoryProvider;
import com.exam.pisti.bots.DummyBotFactory;
import com.exam.pisti.bots.SmartBotFactory;
import com.exam.pisti.components.Bot;
import com.exam.pisti.components.Game;
import com.exam.pisti.components.LeaderBoard;
import com.exam.pisti.enums.BotType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameTest {

    DummyBotFactory dummyBotFactory;
    SmartBotFactory smartBotFactory;

    Game game;
    List<Bot> players;

    @Test
    public void testGamePlay() {
        String result = game.startGame();
        Assert.assertEquals(result, "Success");
        testLeaderBoard();
        /*
        * ------LEADERBOARD------
            Smart-2=47
            Dummy-0=21
            Dummy-1=14
            Smart-3=14
        * */

    }

    public void testLeaderBoard() {
        LeaderBoard.listLeaderBoard();
    }

    @Before
    public void setUp() {
        dummyBotFactory = (DummyBotFactory) BotFactoryProvider.getFactory(BotType.DUMMY);
        smartBotFactory = (SmartBotFactory) BotFactoryProvider.getFactory(BotType.SMART);


        players = Collections.synchronizedList(new ArrayList<Bot>());
        players.add(dummyBotFactory.create("Dummy"));
        players.add(dummyBotFactory.create("Dummy"));
        players.add(smartBotFactory.create("Smart"));
        players.add(smartBotFactory.create("Smart"));

        game = new Game(2, players);


    }


}
