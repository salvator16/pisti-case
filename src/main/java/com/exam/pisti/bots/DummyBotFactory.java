package com.exam.pisti.bots;

import com.exam.pisti.components.DummyBot;
import com.exam.pisti.utils.IdentityNoGenerator;
/**
 * @author ahmet <br>
 *         <b>DummyBotFactory is dummy bot factory
 */
public class DummyBotFactory implements AbstractBotFactory<DummyBot>{

    @Override
    public DummyBot create(String name) {
        DummyBot dummyBot = new DummyBot();
        dummyBot.setName(name);
        dummyBot.setID(IdentityNoGenerator.getBotID());
        return dummyBot;
    }
}
