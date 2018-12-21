package com.tdd;

public class GameBoard {
    private GameEvaluator gameEvaluator;
    private GameMoves moves;

    public GameBoard(GameEvaluator gameEvaluator, GameMoves moves) {
        this.gameEvaluator = gameEvaluator;
        this.moves = moves;
    }

    public void takeSquare(Square square) {
        moves.add(square);
        moves.evaluate(gameEvaluator);
    }
}
