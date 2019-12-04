package com.exam.pisti.bots;

import com.exam.pisti.enums.BotType;
/**
 * @author ahmet <br>
 *         <b>BotFactoryProvider provides spesific type of Bot
 */
public class BotFactoryProvider {

    public static AbstractBotFactory getFactory(BotType botType) {
        switch (botType) {
            case SMART:
                return new SmartBotFactory();
            case DUMMY:
                return new DummyBotFactory();
            default: return null;
        }
    }

}
