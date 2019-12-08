package com.exam.pisti.components;

import com.exam.pisti.constants.GameConstants;
import com.exam.pisti.enums.GameStatus;
import com.exam.pisti.exceptions.EmptyDeckException;

import java.util.List;

/**
 * @author ahmet <br>
 * <b>Dealer manages the single game
 */
public class Dealer {

    private final Pile pile;
    private Deck deck;
    private List<Bot> playerList;
    private GameStatus gameStatus;

    public Dealer(Pile pile, List<Bot> playerList, GameStatus gameStatus) {
        this.pile = pile;
        this.deck = new Deck();
        this.playerList = playerList;
        this.gameStatus = gameStatus;
        injectPileForBots();
    }

    /**
     * Pile class should be static and unique for each game
     * referenced shared for non blocking tread case cause problem.
     * Should be refactored later
     */
    private void injectPileForBots() {
        for (Bot bot : playerList)
            bot.setPile(this.pile);
    }

    protected void deal() {
        try {
            if (gameStatus.getGameTurnStatus().equals(GameStatus.STATUS.START.getValue())) {
                addFourCardToPile();
            }
            if (deck.isCardAvaliableAtDeck()) {
                gameStatus.setGameTurnStatus(GameStatus.STATUS.TURNING);
                dealsFourCardToPlayers();
            } else {
                gameStatus.setGameTurnStatus(GameStatus.STATUS.GAME_OVER);
                if (pile.getBoard().size() > 0) {
                    for (Bot bot : playerList) {
                        if (bot == pile.getLastWinner())
                            bot.receiveCards(pile.getBoard());
                    }
                    pile.getBoard().clear();
                }
            }
        } catch (EmptyDeckException e) {
            e.printStackTrace();
        }

    }

    private void addFourCardToPile() throws EmptyDeckException {
        for (int i = 1; i <= GameConstants.DEAL_COUNT; i++)
            pile.getBoard().push(deck.getCardFromDeck());
    }

    private void dealsFourCardToPlayers() throws EmptyDeckException {
        for (Bot bot : playerList) {
            for (int i = 1; i <= GameConstants.DEAL_COUNT; i++) {
                bot.addCardsToBot(deck.getCardFromDeck());
            }
        }

    }

    protected void prepareToNewGame() {
        this.deck = new Deck();
    }

}
