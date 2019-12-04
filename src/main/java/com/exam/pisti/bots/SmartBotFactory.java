package com.exam.pisti.bots;

import com.exam.pisti.components.SmartBot;
import com.exam.pisti.utils.IdentityNoGenerator;
/**
 * @author ahmet <br>
 *         <b>SmartBotFactory is smart bot factory
 */
public class SmartBotFactory implements AbstractBotFactory<SmartBot> {

    @Override
    public SmartBot create(String name) {
        SmartBot smartBot = new SmartBot();
        smartBot.setName(name);
        smartBot.setID(IdentityNoGenerator.getBotID());
        return smartBot;
    }
}
