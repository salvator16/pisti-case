package com.exam.pisti.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author ahmet <br>
 *         <b>Card static single instance card and ordered deck
 */
public class Card {

    public enum Rank {
        DEUCE, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }

    public enum Suit {
        KUPA, KARO, MACA, SINEK
    }

    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank rank() {
        return rank;
    }

    public Suit suit() {
        return suit;
    }

    private static final List<Card> protoDeck = new ArrayList<Card>();

    static {
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                protoDeck.add(new Card(rank, suit));
    }

    public static final Map<Integer, Rank> lookup = new HashMap<>();

    static {
        int i = 1;
        for (Rank d : Rank.values()) {
            lookup.put(i , d);
            i++;
        }
    }

    /**
    * Only ranked checked as equal
     * */

    public static ArrayList<Card> newDeck() {
        return new ArrayList<Card>(protoDeck);
    }

    public static Card getJack() {
        return new Card(Rank.JACK, null);
    }

}
