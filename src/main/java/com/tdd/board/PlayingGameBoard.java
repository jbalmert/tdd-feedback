package com.tdd.board;

import com.tdd.player.CurrentPlayerAware;
import com.tdd.player.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayingGameBoard implements CurrentPlayerAware, GameBoard {
    private final GameEvents events;
    private Map<Player, Squares> playerToSquares = new HashMap<>();
    private Player currentPlayer;

    public PlayingGameBoard(Squares xSquares, Squares oSquares, GameEvents events) {
        this.events = events;
        playerToSquares.put(Player.X, xSquares);
        playerToSquares.put(Player.O, oSquares);
    }

    @Override
    public void currentPlayer(Player player) {
        currentPlayer = player;
    }

    @Override
    public void takeSquare(Square square) {
        events.move(currentPlayer, square);
        playerToSquares.get(currentPlayer).add(square);
    }
}