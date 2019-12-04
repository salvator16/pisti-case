package com.exam.pisti.enums;

/**
 * @author ahmet <br>
 * <b>GameStatus class tracks game stages
 */

public class GameStatus {

    private String gameTurnStatus = STATUS.NONE.getValue();

    public enum STATUS {

        NONE("NONE"), START("START"), TURNING("TURNING"), GAME_OVER("GAME_OVER");

        private String value;

        STATUS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public  synchronized String getGameTurnStatus() {
        return gameTurnStatus;
    }

    public synchronized void setGameTurnStatus(STATUS status) {
        gameTurnStatus = status.getValue();
    }

}
