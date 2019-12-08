package com.exam.pisti.components;

import java.util.Stack;


/**
 * @author ahmet <br>
 */

public class Pile {

    private Stack<Card> board = new Stack<>();
    private Stack<Card> boardMemory = new Stack<>();
    private Bot lastWinner;

    public Stack<Card> getBoard() {
        return board;
    }

    public Stack<Card> getBoardMemory() {
        return boardMemory;
    }

    public Bot getLastWinner() {
        return lastWinner;
    }

    public void setLastWinner(Bot lastWinner) {
        this.lastWinner = lastWinner;
    }
}
