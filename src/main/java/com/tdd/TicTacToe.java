package com.tdd;

import com.tdd.player.PlayerTracker;

public class TicTacToe {
    private GameDisplay display;
    private GameBoard board;
    private GameEvaluator gameEvaluator;
    private PlayerTracker playerTracker;

    public TicTacToe(GameDisplay display, GameBoard board, GameEvaluator gameEvaluator, PlayerTracker playerTracker) {
        this.display = display;
        this.board = board;
        this.gameEvaluator = gameEvaluator;
        this.playerTracker = playerTracker;
    }

    public void place(Square square) {
        playerTracker.nextTurn();
        board.takeSquare(square);
        board.evaluateState(gameEvaluator);
        board.display(display);
    }
}
