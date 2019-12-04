package com.exam.pisti.factoryTest;

import com.exam.pisti.bots.BotFactoryProvider;
import com.exam.pisti.bots.DummyBotFactory;
import com.exam.pisti.bots.SmartBotFactory;
import com.exam.pisti.enums.BotType;
import com.exam.pisti.components.DummyBot;
import com.exam.pisti.components.SmartBot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * @author ahmet <br>
 */

public class TestBotFactory {

    DummyBotFactory dummyBotFactory;
    SmartBotFactory smartBotFactory;

    @Before
    public void setUp(){
        dummyBotFactory = (DummyBotFactory) BotFactoryProvider.getFactory(BotType.DUMMY);
        smartBotFactory = (SmartBotFactory) BotFactoryProvider.getFactory(BotType.SMART);
    }

    @Test
    public void testDummyBotFactory() {
        DummyBot dummyBot = new DummyBot();
        dummyBot.setName("TestDummyBot");
        dummyBot.setID("0");
        DummyBot dummyBotCreate = dummyBotFactory.create("TestDummyBot");
        assertEquals(dummyBot,dummyBotCreate);
    }

    @Test
    public void testSmartBotFactory() {
        SmartBot smartBot = new SmartBot();
        smartBot.setName("TestSmartBot");
        smartBot.setID("0");
        SmartBot smartBotCreate = smartBotFactory.create("TestSmartBot");
        assertEquals(smartBot,smartBotCreate);
    }


}
