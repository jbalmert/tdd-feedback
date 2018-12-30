package com.tdd.board;

import com.tdd.player.CurrentPlayerAware;
import com.tdd.player.Player;

import java.util.Set;

public class GameEvaluator implements CurrentPlayerAware {
    private GameEvents events;
    private Solutions solutions;
    private Player currentPlayer;
    private static int MAX_PLAYER_MOVES = 5;

    public GameEvaluator(GameEvents events, Solutions solutions) {
        this.events = events;
        this.solutions = solutions;
    }

    public void evaluate(Set<Square> squares) {
        boolean hasWinner = solutions.matches(squares);
        if (hasWinner) {
            events.wins(currentPlayer);
        } else if (squares.size() == MAX_PLAYER_MOVES) {
            events.draw();
        }
    }

    @Override
    public void currentPlayer(Player player) {
        currentPlayer = player;
    }
}
