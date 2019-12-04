package com.exam.pisti.utils;

import com.exam.pisti.components.Card;

import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

public class CardUtils {

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue()))
                return entry.getKey();
        }
        return null;
    }


    public static Card.Rank getMinRank(LinkedBlockingDeque<Card> cards) {

        int smallestIndex = 15;
        synchronized (cards){
            for (Card card : cards){
                int tempIndex = getKey(Card.lookup ,card.rank());
                if (smallestIndex >= tempIndex )
                    smallestIndex = tempIndex;
            }
        }
        return Card.lookup.get(smallestIndex);
    }

}
