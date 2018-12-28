package com.tdd;

import com.tdd.player.Player;

import java.util.HashMap;
import java.util.Map;

public class GameBoard implements CurrentPlayerAware {
    private final GameEvents events;
    private Map<Player, Squares> playerToSquares = new HashMap<>();
    private Player currentPlayer;

    public GameBoard(Squares xSquares, Squares oSquares, GameEvents events) {
        this.events = events;
        playerToSquares.put(Player.X, xSquares);
        playerToSquares.put(Player.O, oSquares);
    }

    @Override
    public void currentPlayer(Player player) {
        currentPlayer = player;
    }

    public void takeSquare(Square square) {
        playerToSquares.get(currentPlayer).add(square);
        events.move(currentPlayer, square);
    }
}
