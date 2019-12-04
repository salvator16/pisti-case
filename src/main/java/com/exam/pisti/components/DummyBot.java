package com.exam.pisti.components;

import com.exam.pisti.constants.GameConstants;
import com.exam.pisti.utils.CardUtils;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
/**
 * @author ahmet <br>
 * <b>Dummy bot class symbolize player type in game
 * Designed to be playing simple
 */
public class DummyBot extends Bot {

    private LinkedBlockingDeque<Card> playerHand;
    private LinkedBlockingDeque<Card> collectedCards;

    public DummyBot() {
        this.playerHand = new LinkedBlockingDeque<Card>();
        this.collectedCards = new LinkedBlockingDeque<Card>();

    }

    @Override
    public void addCardsToBot(Card card) throws InterruptedException {
        playerHand.put(card);
    }

    @Override
    public void play() throws InterruptedException {
        /**
         * Choose card to play
         * */
        Card card = decideCardToPlay();
        /**
         * check win  pile cards condition
         * */
        if (isWin(card)) {
            isPisti(card);
            getPile().getBoard().put(card);
            operateBoardActions(collectedCards);

        } else {
            getPile().getBoard().put(card);
        }

        /**
         *  Remove card from player hand
         *  */
        playerHand.remove(card);

    }

    @Override
    public void receiveCards(LinkedBlockingDeque<Card> cards) {
        collectedCards.addAll(cards);
    }

    @Override
    public Card decideCardToPlay() {
        Card card = null;
        if (getPile().getBoard().size() > 0) {
            card = findCard(getPile().getBoard().peekLast().rank(), playerHand);
            if (card == null)
                card = hasJack();
        }
        if (card == null)
            card = findCard(CardUtils.getMinRank(playerHand), playerHand);

        return card;
    }

    @Override
    public Card hasJack() {
        return findCard(Card.getJack().rank(), playerHand);
    }

    @Override
    public boolean isCardAvailable() {
        return playerHand.size() > 0 ? true : false;
    }

    @Override
    public boolean isWin(Card card) {
        return super.isWin(card);
    }

    @Override
    public boolean isPisti(Card card) {
        if (getPile().getBoard().size() == 1) {
            int score = getScore();
            score = score + GameConstants.EACH_PISTI;
            if (card.rank().equals(Card.Rank.JACK))
                score = score + GameConstants.EACH_PISTI;
            setScore(score);
            return true;
        }
        return false;
    }
    /**
     * Calculate score based on collected cards
     * then flush collected card for next iteration of game
     */
    @Override
    public void calculateScore(LinkedBlockingDeque<Card> collectedCards) {
        super.calculateScore(collectedCards);
        collectedCards.clear();
    }

    @Override
    public void disposePlayer() {
        calculateScore(this.collectedCards);
    }

}
