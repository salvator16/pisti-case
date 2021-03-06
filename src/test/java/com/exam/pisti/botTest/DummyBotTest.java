package com.exam.pisti.botTest;

import com.exam.pisti.components.Deck;
import com.exam.pisti.components.DummyBot;
import com.exam.pisti.components.Pile;
import com.exam.pisti.exceptions.EmptyDeckException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DummyBotTest {

    DummyBot bot;
    Deck deck;
    Pile pile;

    @Test
    public void smartBotActionTest(){
        try {
            Assert.assertNotNull(bot);
            Assert.assertEquals(bot.getUsername(), "Dummy-12");
            bot.play();
            Assert.assertEquals(pile.getBoard().size(), 1);
            bot.play();
            Assert.assertEquals(pile.getBoard().size(), 2);

            Assert.assertEquals(bot.isCardAvailable(), true);


        } catch (Exception e) {
            Assert.fail("Cannot play something went wrong");
        }

    }

    @Before
    public void setUp(){
        deck = new Deck();
        pile = new Pile();
        bot = new DummyBot();
        bot.setName("Dummy");
        bot.setID("12");
        bot.setPile(pile);
        try {
            bot.addCardsToBot(deck.getCardFromDeck());
            bot.addCardsToBot(deck.getCardFromDeck());
            bot.addCardsToBot(deck.getCardFromDeck());
            bot.addCardsToBot(deck.getCardFromDeck());
        } catch (EmptyDeckException e) {
            Assert.fail("Deck Bos");
        } catch (Exception e) {
            Assert.fail("Operation Interrupted");
        }


    }

}
