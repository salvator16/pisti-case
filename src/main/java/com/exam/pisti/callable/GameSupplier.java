package com.exam.pisti.callable;

import com.exam.pisti.components.Game;

import java.util.function.Supplier;
/**
 * @author ahmet <br>
 *         <b>GameSupplier class just holding it to test with joinFork executor pool
 *         with blocking stages. Latest version its unnecessary.
 */
public class GameSupplier implements Supplier<Void> {

    private final Game game;

    public GameSupplier(Game game) {
        this.game = game;
    }

    @Override
    public Void get() {
        try {
            game.startGame();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
