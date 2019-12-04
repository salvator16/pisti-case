package com.exam.pisti.services;

import com.exam.pisti.callable.GameSupplier;
import com.exam.pisti.components.Bot;
import com.exam.pisti.components.Game;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ahmet <br>
 * <b>GameService starts async processes and initiliaze jvm executor
 */

public class GameService {

    ExecutorService executorService = Executors.newFixedThreadPool(20);

    public CompletableFuture<String> startAsyncGame(int gameCount, List<Bot> players) {
        Game game = new Game(gameCount,players);
        CompletableFuture<String> future = new CompletableFuture<>();
        return future.supplyAsync(() -> game.startGame(), executorService);
    }

    /**
     * This method implemented for observing futures block case
     * Only for monitoring purposes
     */
    public void startAsyncGameWithBlocking(int gameCount, List<Bot> players) {
        Game game = new Game(gameCount,players);
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new GameSupplier(game), executorService);
        future.join();
    }

    public void shutDown() {
        executorService.shutdown();
    }
}
