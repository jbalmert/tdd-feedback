package com.tdd;

import java.util.Set;

public class GameEvaluator {
    private GameEvents events;
    private Set<Solution> solutions;

    public GameEvaluator(GameEvents events, Set<Solution> solutions) {
        this.events = events;
        this.solutions = solutions;
    }

    public void evaluate(Set<Square> squares) {
        boolean hasWinner = false;
        for (Solution solution: solutions){
            hasWinner = hasWinner || solution.matches(squares);
        }
        if (hasWinner) {
            events.xWins();
        }
    }
}
