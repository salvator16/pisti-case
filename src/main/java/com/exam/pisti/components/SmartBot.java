package com.exam.pisti.components;

import com.exam.pisti.constants.GameConstants;
import com.exam.pisti.utils.CardUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ahmet <br>
 * <b>Smart bot class symbolize player type in game
 * Designed to be playing with analyze and memorize pile
 */


public class SmartBot extends Bot {

    private LinkedBlockingDeque<Card> playerHand;
    private LinkedBlockingDeque<Card> collectedCards;


    public SmartBot() {
        this.playerHand = new LinkedBlockingDeque<Card>();
        this.collectedCards = new LinkedBlockingDeque<>();
    }

    @Override
    public void addCardsToBot(Card card) throws InterruptedException {
        playerHand.put(card);
    }

    @Override
    public void play() throws InterruptedException {
        Card card = decideCardToPlay();

        if (isWin(card)) {
            isPisti(card);
            getPile().getBoard().put(card);
            operateBoardActions(collectedCards);
        } else {
            getPile().getBoard().put(card);
        }
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
            card = findCard(getPile().getBoard().peekLast().rank(),playerHand);
            if (card == null)
                card = hasJack();
        }

        /**
         * smart analyze and decide to card
         * try to find most discarded card to the pile
         * this way, the chance of other players to win pile, reduced
         * */
        if (card == null) {
            card = analyzeCard();
        }

        return card;
    }

    @Override
    public Card hasJack() {
        return findCard(Card.getJack().rank(),playerHand);
    }

    @Override
    public boolean isCardAvailable() {
        return playerHand.size() > 0 ? true : false;
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

    @Override
    public boolean isWin(Card card) {
        return super.isWin(card);
    }

    @Override
    public void calculateScore(LinkedBlockingDeque<Card> collectedCards) {
        super.calculateScore(collectedCards);
        collectedCards.clear();
        getPile().getBoardMemory().clear();
    }

    @Override
    public void disposePlayer() {
        calculateScore(this.collectedCards);
    }

    private synchronized Card analyzeCard() {
        Map<Card.Rank, Long> grouped = new ConcurrentHashMap<>();
        synchronized (getPile().getBoardMemory()) {
            if (getPile().getBoardMemory().size() > 0) {
                grouped = getPile().getBoardMemory().stream()
                        .map(Card::rank)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            }
        }

        long countOfCard = 0;
        long maxNo = countOfCard;
        Card.Rank rank = CardUtils.getMinRank(playerHand);
        synchronized (playerHand) {
            for (Card card1 : playerHand) {
                countOfCard = grouped.get(card1.rank()) == null ? 0 : grouped.get(card1.rank());
                if (countOfCard > maxNo) {
                    maxNo = countOfCard;
                    rank = card1.rank();
                }
            }
        }
        return findCard(rank,playerHand);

    }


}
