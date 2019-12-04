package com.exam.pisti.components;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author ahmet <br>
 * Defines possible actions in game
 */

public interface GameActions {

    void play() throws InterruptedException;

    void receiveCards(LinkedBlockingDeque<Card> cards);

    void calculateScore(LinkedBlockingDeque<Card> collectedCards);

    void disposePlayer();

    Card hasJack();

    Card decideCardToPlay();

    boolean isCardAvailable();

    boolean isPisti(Card card);

    boolean isWin(Card card);
}
