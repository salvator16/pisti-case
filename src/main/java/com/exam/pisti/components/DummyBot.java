package com.exam.pisti.components;

import com.exam.pisti.utils.CardUtils;

import java.util.Stack;
/**
 * @author ahmet <br>
 * <b>Dummy bot class symbolize player type in game
 * Designed to be playing simple
 */
public class DummyBot extends Bot {

    private Stack<Card> playerHand;
    private Stack<Card> collectedCards;

    public DummyBot() {
        this.playerHand = new Stack<>();
        this.collectedCards = new Stack<>();
    }

    @Override
    public void addCardsToBot(Card card) {
        playerHand.push(card);
    }

    @Override
    public void play() {
        /**
         * Choose card to play
         * */
        Card card = decideCardToPlay();
        /**
         * check win  pile cards condition
         * */
        if (isWin(card)) {
            isPisti(card);
            getPile().getBoard().push(card);
            operateBoardActions(collectedCards);

        } else {
            getPile().getBoard().push(card);
        }

        /**
         *  Remove card from player hand
         *  */
        playerHand.remove(card);

    }

    @Override
    public void receiveCards(Stack<Card> cards) {
        collectedCards.addAll(cards);
    }

    @Override
    public Card decideCardToPlay() {
        Card card = null;
        if (getPile().getBoard().size() > 0) {
            card = findCard(getPile().getBoard().peek().rank(), playerHand);
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
       return super.isPisti(card);
    }
    /**
     * Calculate score based on collected cards
     * then flush collected card for next iteration of game
     */
    @Override
    public void calculateScore(Stack<Card> collectedCards) {
        super.calculateScore(collectedCards);
        collectedCards.clear();
    }

    @Override
    public void disposePlayer() {
        calculateScore(this.collectedCards);
    }

}
