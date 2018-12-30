package com.tdd;

import com.tdd.board.PlayingGameBoard;
import com.tdd.board.Square;
import com.tdd.player.PlayerTracker;

public class TicTacToe {
    private PlayingGameBoard board;
    private PlayerTracker playerTracker;

    public TicTacToe(PlayingGameBoard board, PlayerTracker playerTracker) {
        this.board = board;
        this.playerTracker = playerTracker;
    }

    public void place(Square square) {
        playerTracker.nextTurn();
        board.takeSquare(square);
    }
}
