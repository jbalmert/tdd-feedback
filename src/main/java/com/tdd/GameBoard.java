package com.tdd;

import com.tdd.player.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameBoard implements CurrentPlayerAware{
    private GameEvaluator gameEvaluator;
    private Player currentPlayer;
    private Set<Square> playerXMoves = new HashSet<>();
    private Set<Square> playerOMoves = new HashSet<>();
    private Map<Player, Set<Square>> playersMoveMap = new HashMap<>();

    public GameBoard(GameEvaluator gameEvaluator) {
        playersMoveMap.put(Player.X, playerXMoves);
        playersMoveMap.put(Player.O, playerOMoves);
        this.gameEvaluator = gameEvaluator;
    }

    public void takeSquare(Square square) {
        Set<Square> moves = playersMoveMap.get(currentPlayer);
        moves.add(square);
        this.gameEvaluator.evaluate(moves);
    }

    @Override
    public void currentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
