package com.tdd.board;

import com.tdd.player.CurrentPlayerAware;
import com.tdd.player.Player;

import java.util.Set;

public class GameEvaluator implements CurrentPlayerAware {
    private GameEvents events;
    private Solutions solutions;
    private Player currentPlayer;

    public GameEvaluator(GameEvents events, Solutions solutions) {
        this.events = events;
        this.solutions = solutions;
    }

    public void evaluate(Set<Square> squares) {
        boolean hasWinner = solutions.matches(squares);
        if (hasWinner) {
            events.wins(currentPlayer);
        } else if (squares.size() == 5) {
            events.draw();
        }
    }

    @Override
    public void currentPlayer(Player player) {
        currentPlayer = player;
    }
}
