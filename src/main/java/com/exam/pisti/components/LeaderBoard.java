package com.exam.pisti.components;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ahmet <br>
 * <b>LeaderBoard map can be updated anytime by different bots(threads)
 * At the end of the game lists scoreBoard in desc order
 */

public class LeaderBoard {

    private static final Map<String, Integer> leaderboardMap = new ConcurrentHashMap<>();

    private LeaderBoard() {
    }

    public static void registerBotScore(Bot bot) {
        int tempScore = 0;
        if (leaderboardMap.containsKey(bot.getUsername()))
            tempScore = leaderboardMap.get(bot.getUsername());
        leaderboardMap.put(bot.getUsername(), tempScore + bot.getScore());
    }

    public static void listLeaderBoard() {
        System.out.println("------LEADERBOARD------");
        leaderboardMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);

    }
}
