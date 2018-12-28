package com.tdd;

import com.tdd.board.GameBoard;
import com.tdd.board.Square;
import com.tdd.player.PlayerTracker;

public class TicTacToe {
    private GameBoard board;
    private PlayerTracker playerTracker;

    public TicTacToe(GameBoard board, PlayerTracker playerTracker) {
        this.board = board;
        this.playerTracker = playerTracker;
    }

    public void place(Square square) {
        playerTracker.nextTurn();
        board.takeSquare(square);
    }
}
