package com.exam.pisti.components;

import com.exam.pisti.enums.GameStatus;

import java.util.List;
/**
 * @author ahmet <br>
 * <b>Game class represents one single game repeated x times
 * Also defines and intiliaze other releated Objects
 */
public class Game {

    private final Dealer dealer;
    private final Pile pile;
    private final List<Bot> players;
    private final GameStatus gameStatus;
    private final int repeatCount;

    public Game(int repeatCount, List<Bot> players) {
        this.pile = new Pile();
        this.players = players;
        this.gameStatus = new GameStatus();
        this.dealer = new Dealer(pile, players, gameStatus);
        this.repeatCount = repeatCount;
    }
    /**
     * startGame function has return type just for symbolize future s type
     * can be deleted later
     * */

    public String startGame() {
        for (int i = 1; i <= repeatCount; i++) {
            try {
                executeGame();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dealer.prepareToNewGame();
        }
        return "Success";
    }

    private void executeGame() throws InterruptedException {
        gameStatus.setGameTurnStatus(GameStatus.STATUS.START);
        dealer.deal();
        while (gameStatus.getGameTurnStatus().equals(GameStatus.STATUS.START.getValue())
                || gameStatus.getGameTurnStatus().equals(GameStatus.STATUS.TURNING.getValue())) {
                for (Bot bot : players) {
                    if (!players.get(3).isCardAvailable()) {
                        dealer.deal();
                    }
                    if (!gameStatus.getGameTurnStatus().equals(GameStatus.STATUS.GAME_OVER.getValue())) {
                        bot.play();
                    } else {
                        bot.disposePlayer();
                    }
                }
        }
    }
}
