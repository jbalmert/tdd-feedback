package com.tdd.board;

import com.tdd.player.Player;

public class EndGameListener implements GameEvents {
    private GameStateAware gameStateAware;

    public EndGameListener(GameStateAware gameStateAware) {
        this.gameStateAware = gameStateAware;
    }

    @Override
    public void move(Player player, Square square) {

    }

    @Override
    public void invalidMove(Player player, Square square) {

    }

    @Override
    public void wins(Player player) {
        gameStateAware.gameEnded();
    }

    @Override
    public void draw() {
        gameStateAware.gameEnded();
    }

    @Override
    public void gameOver() {

    }
}
