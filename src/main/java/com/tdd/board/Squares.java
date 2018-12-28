package com.tdd.board;


import java.util.HashSet;
import java.util.Set;

public class Squares {
    private GameEvaluator gameEvaluator;
    private Set<Square> squares = new HashSet<>();

    public Squares(GameEvaluator gameEvaluator) {
        this.gameEvaluator = gameEvaluator;
    }

    public void add(Square square) {
        squares.add(square);
        gameEvaluator.evaluate(squares);
    }
}
