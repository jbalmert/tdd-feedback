package com.tdd;

public class PlayerTracker {
    private CurrentPlayerAware currentPlayerAware;
    private Player currentPlayer = Player.X;

    public void nextTurn() {
        currentPlayerAware.currentPlayer(currentPlayer);
        togglePlayer();
    }

    public void register(CurrentPlayerAware currentPlayerAware) {
        this.currentPlayerAware = currentPlayerAware;
    }

    private void togglePlayer() {
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }
}
