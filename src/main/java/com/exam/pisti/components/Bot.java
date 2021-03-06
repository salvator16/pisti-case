package com.exam.pisti.components;

import com.exam.pisti.constants.GameConstants;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;


/**
 * @author ahmet <br>
 * <b>Bot is abstract class which defines default features for Smart and Dummy
 * also implements GameActions that actions received naturally by Smart and Dummy childs.
 */
public abstract class Bot implements GameActions, Serializable {

    private static final long serialVersionUID = -6797213701714019307L;

    private String name;
    private String ID;
    private Integer score = 0;
    private Pile pile;

    Bot() {
    }


    public void addCardsToBot(Card card)  {
    }

    @Override
    public void play() {
    }

    @Override
    public void receiveCards(Stack<Card> cards) {

    }

    @Override
    public Card decideCardToPlay() {
        return null;
    }

    @Override
    public Card hasJack() {
        return null;
    }

    @Override
    public boolean isCardAvailable() {
        return false;
    }

    @Override
    public boolean isPisti(Card card) {
        if (getPile().getBoard().size() == 1) {
            int score = getScore();
            score = score + GameConstants.EACH_PISTI;
            if (card.rank().equals(Card.Rank.JACK))
                score = score + GameConstants.EACH_PISTI;
            setScore(score);
            return true;
        }
        return false;
    }

    @Override
    public boolean isWin(Card card) {
            if (pile.getBoard().size() > 0) {
                if ((card.rank().equals(pile.getBoard().peek().rank()) || card.rank().equals(Card.Rank.JACK)))
                    return true;
            }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof Bot) {
                Bot bot = (Bot) obj;
                return this.getName().equals(bot.getName());
            } else
                return false;
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return "Bot features {" + "name='" + name + '\'' +
                ", ID=" + ID +
                ", score=" + score +
                '}';
    }

    /**
     * search stack o(1) and calculate total score, each pisti and jack pisti score already calculated,
     * didn't use stack.search method cause I already customized it for rank searching
     */

    @Override
    public void calculateScore(Stack<Card> collectedCards) {
            for (Card card : collectedCards) {
                if (card.rank().equals(Card.Rank.JACK) || card.rank().equals(Card.Rank.ACE))
                    score = score + GameConstants.SINGLE_POINT;
                if (card.rank().equals(Card.Rank.DEUCE) && card.suit().equals(Card.Suit.SINEK))
                    score = score + GameConstants.DOUBLE_POINT;
                if (card.rank().equals(Card.Rank.TEN) && card.suit().equals(Card.Suit.KARO))
                    score = score + GameConstants.TRIPLE_POINT;
            }
            if (pile.getLastWinner().equals(getUsername()))
                score = score + GameConstants.TRIPLE_POINT;

            LeaderBoard.registerBotScore(this);

            this.setScore(0);
    }

    @Override
    public void disposePlayer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return name + "-" + ID;
    }

    public Pile getPile() {
        return pile;
    }

    public void setPile(Pile pile) {
        this.pile = pile;
    }

    protected void operateBoardActions(Stack<Card> collectedCards) {
        collectedCards.addAll(getPile().getBoard());
        getPile().getBoardMemory().addAll(getPile().getBoard());
        getPile().getBoard().clear();
        getPile().setLastWinner(this);
    }

    protected Card findCard(Card.Rank rank, Stack<Card> playerHand) {
        Iterator itr = playerHand.iterator();
        Card card;
        while (itr.hasNext()) {
            card = (Card) itr.next();
            if (card.rank().equals(rank))
                return card;
        }
        return null;
    }

}
