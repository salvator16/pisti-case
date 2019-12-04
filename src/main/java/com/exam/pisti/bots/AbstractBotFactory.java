package com.exam.pisti.bots;

/**
 * @author ahmet <br>
 *         <b>AbstractBotFactory  class to generate any type of Bot
 */
public interface AbstractBotFactory<T> {

    T create(String name);

}
