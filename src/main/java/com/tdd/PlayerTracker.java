package com.tdd;

public class PlayerTracker {
    private CurrentPlayerAware currentPlayerAware;
    private Player currentPlayer = Player.X;

    public void nextTurn() {
        currentPlayerAware.currentPlayer(currentPlayer);
    }

    public void register(CurrentPlayerAware currentPlayerAware) {

        this.currentPlayerAware = currentPlayerAware;
    }
}
