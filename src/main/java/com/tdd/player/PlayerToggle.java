package com.tdd.player;

public class PlayerToggle {
    Player currentPlayer = Player.O;

    public Player nextPlayer() {
        currentPlayer = calculateNextPlayer();
        return currentPlayer;
    }

    private Player calculateNextPlayer() {
        return currentPlayer == Player.X ? Player.O : Player.X;
    }
}
