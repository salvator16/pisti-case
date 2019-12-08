package com.exam.pisti;

import com.exam.pisti.bots.BotFactoryProvider;
import com.exam.pisti.bots.DummyBotFactory;
import com.exam.pisti.bots.SmartBotFactory;
import com.exam.pisti.components.Bot;
import com.exam.pisti.constants.GameConstants;
import com.exam.pisti.enums.BotType;
import com.exam.pisti.services.GameService;
import com.exam.pisti.components.LeaderBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author ahmet <br>
 *         <b>com.exam.pist.PistiApplication  class is the starting Class
 */


@SpringBootApplication
public class PistiApplication {

    public static void main(String[] args) {

        SpringApplication.run(PistiApplication.class, args);

        Scanner in = new Scanner(System.in);
        System.out.println("--------Enter Player Count-------- :");
        int playerCount = in.nextInt();
        System.out.println("--------Enter Game repeat Count-------- :");
        int gameRepeat = in.nextInt();
        System.out.println("--------Enter Dummy Bot Name -------- :");
        String dummyBot = in.next();
        System.out.println("--------Enter Smart Bot Name -------- :");
        String smartBot = in.next();

        DummyBotFactory factoryDummy = (DummyBotFactory) BotFactoryProvider.getFactory(BotType.DUMMY);
        SmartBotFactory factorySmart = (SmartBotFactory) BotFactoryProvider.getFactory(BotType.SMART);
        GameService gameService = new GameService();

        List<Bot> players;
        List<CompletableFuture<String>> futuresList = new ArrayList<>();

        int gameCount = Math.floorDiv( playerCount, GameConstants.ROOM_SIZE) ;
        while (gameCount > 0) {
            /**
             * Stateless list cause problem with for loop,
             * That s why its important to define player list for
             * each game as synchronizedList
             * */
            players = Collections.synchronizedList(new ArrayList<>());

            players.add(factoryDummy.create(dummyBot));
            players.add(factorySmart.create(smartBot));
            players.add(factoryDummy.create(dummyBot));
            players.add(factorySmart.create(smartBot));
            /**
             * Adding all paralel futures in to list without BLOCKING!!!
             * Until next step most of the future.isDone() status are false
             * */
            futuresList.add(gameService.startAsyncGame(gameRepeat, players));
            gameCount--;
        }

        CompletableFuture<List<String>> combinedFuture = all(futuresList);
        try {
            /**
             * Make sure that all features done before shutdown executor service
             * and print the leaderboard
             * */
            combinedFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            gameService.shutDown();
        }
        LeaderBoard.listLeaderBoard();
    }

    /**
     * Completable future allOf methods does not accept collections yet
     *
     * */

    public static <T> CompletableFuture<List<T>> all(List<CompletableFuture<T>> futures) {
        CompletableFuture[] gameFutures = futures.toArray(new CompletableFuture[futures.size()]);
        return CompletableFuture.allOf(gameFutures)
                .thenApply(ignored -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
                );
    }
}
