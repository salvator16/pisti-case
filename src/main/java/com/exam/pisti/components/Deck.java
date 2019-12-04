package com.exam.pisti.components;

import com.exam.pisti.exceptions.EmptyDeckException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author ahmet <br>
 * <b>Deck class generates shuffled deck for dealer
 */

public class Deck {

    private LinkedBlockingDeque<Card> deck = new LinkedBlockingDeque<Card>() ;

    public Deck() {
        initializeDeck();
    }

    private void initializeDeck() {
        deck = getShuffledDeck();
    }

    private LinkedBlockingDeque<Card> getShuffledDeck() {
        List<Card> cardList = Card.newDeck();
        Collections.shuffle(cardList);
        deck.addAll(cardList);
        return deck;
    }

    public  Card getCardFromDeck() throws EmptyDeckException {
        if (deck.isEmpty())
            throw new EmptyDeckException("Deck is empty");
        return deck.pop();
    }

    public boolean isCardAvaliableAtDeck() {
        return deck.size() > 0 ? true : false;
    }


}
