package com.exam.pisti.components;

        import java.util.Stack;

/**
 * @author ahmet <br>
 * Defines possible actions in game
 */

public interface GameActions {

    void play();

    void receiveCards(Stack<Card> cards);

    void calculateScore(Stack<Card> collectedCards);

    void disposePlayer();

    Card hasJack();

    Card decideCardToPlay();

    boolean isCardAvailable();

    boolean isPisti(Card card);

    boolean isWin(Card card);
}
