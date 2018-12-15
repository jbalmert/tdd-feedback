package com.tdd;

public class TicTacToe {
    private GameDisplay display;
    private GameBoard board;
    private GameEvaluator gameEvaluator;

    public TicTacToe(GameDisplay display, GameBoard board, GameEvaluator gameEvaluator) {
        this.display = display;
        this.board = board;
        this.gameEvaluator = gameEvaluator;
    }

    public void placeXOn(Square square) {
        board.takeSquare(Player.X, square);
        gameEvaluator.evaluateState(board);
    }

    public void placeOOn(Square square) {
    }
}
