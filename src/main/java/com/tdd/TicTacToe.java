package com.tdd;

public class TicTacToe {
    private GameDisplay display;
    private GameBoard board;

    public TicTacToe(GameDisplay display, GameBoard board) {
        this.display = display;
        this.board = board;
    }

    public void placeXOn(Square square) {
        board.takeSquare(Player.X, square);
    }

    public void placeOOn(Square square) {
    }
}
