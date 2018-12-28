package com.tdd;

import java.util.Set;

public class GameEvaluator {
    private GameEvents events;
    private Solutions solutions;

    public GameEvaluator(GameEvents events, Solutions solutions) {
        this.events = events;
        this.solutions = solutions;
    }

    public void evaluate(Set<Square> squares) {
        boolean hasWinner = solutions.matches(squares);
        if (hasWinner) {
            events.xWins();
        }
    }
}
