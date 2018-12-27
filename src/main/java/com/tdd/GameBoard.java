package com.tdd;

import com.tdd.player.Player;

import java.util.HashMap;
import java.util.Map;

public class GameBoard implements CurrentPlayerAware {
    private Map<Player, Squares> playerToSquares = new HashMap<>();
    private Player currentPlayer;

    public GameBoard(Squares xSquares, Squares oSquares) {
        playerToSquares.put(Player.X, xSquares);
        playerToSquares.put(Player.O, oSquares);
    }

    @Override
    public void currentPlayer(Player player) {
        currentPlayer = player;
    }

    public void takeSquare(Square square) {
        playerToSquares.get(currentPlayer).add(square);
    }
}
