package com.exam.pisti.components;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author ahmet <br>
 */

public class Pile {

    private LinkedBlockingDeque<Card> board = new LinkedBlockingDeque<>();
    private LinkedBlockingDeque<Card> boardMemory = new LinkedBlockingDeque<>();
    private Bot lastWinner;

    public LinkedBlockingDeque<Card> getBoard() {
        return board;
    }

    public LinkedBlockingDeque<Card> getBoardMemory() {
        return boardMemory;
    }

    public Bot getLastWinner() {
        return lastWinner;
    }

    public void setLastWinner(Bot lastWinner) {
        this.lastWinner = lastWinner;
    }
}
