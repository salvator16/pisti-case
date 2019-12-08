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

    }

    public String getGameTurnStatus() {
        return gameTurnStatus;
    }

    public void setGameTurnStatus(STATUS status) {
        gameTurnStatus = status.getValue();
    }

}
