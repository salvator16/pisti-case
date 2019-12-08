package com.exam.pisti.components;

import com.exam.pisti.utils.CardUtils;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ahmet <br>
 * <b>Smart bot class symbolize player type in game
 * Designed to be playing with analyze and memorize pile
 */


public class SmartBot extends Bot {

    private Stack<Card> playerHand;
    private Stack<Card> collectedCards;


    public SmartBot() {
        this.playerHand = new Stack<>();
        this.collectedCards = new Stack<>();
    }

    @Override
    public void addCardsToBot(Card card) {
        playerHand.push(card);
    }

    @Override
    public void play() {
        Card card = decideCardToPlay();

        if (isWin(card)) {
            isPisti(card);
            getPile().getBoard().push(card);
            operateBoardActions(collectedCards);
        } else {
            getPile().getBoard().push(card);
        }
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
            card = findCard(getPile().getBoard().peek().rank(),playerHand);
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
        return super.isPisti(card);
    }

    @Override
    public boolean isWin(Card card) {
        return super.isWin(card);
    }

    @Override
    public void calculateScore(Stack<Card> collectedCards) {
        super.calculateScore(collectedCards);
        collectedCards.clear();
        getPile().getBoardMemory().clear();
    }

    @Override
    public void disposePlayer() {
        calculateScore(this.collectedCards);
    }

    private Card analyzeCard() {
        Map<Card.Rank, Long> grouped = new ConcurrentHashMap<>();
            if (getPile().getBoardMemory().size() > 0) {
                grouped = getPile().getBoardMemory().stream()
                        .map(Card::rank)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            }

        long countOfCard = 0;
        long maxNo = countOfCard;
        Card.Rank rank = CardUtils.getMinRank(playerHand);
            for (Card card1 : playerHand) {
                countOfCard = grouped.get(card1.rank()) == null ? 0 : grouped.get(card1.rank());
                if (countOfCard > maxNo) {
                    maxNo = countOfCard;
                    rank = card1.rank();
                }
            }
        return findCard(rank,playerHand);

    }


}
