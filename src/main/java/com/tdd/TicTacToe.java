package com.tdd;

public class TicTacToe {
    private GameDisplay display;
    private GameBoard board;

    public TicTacToe(GameDisplay display, GameBoard board) {
        this.display = display;
        this.board = board;
    }

    public void placeXOn(GameColumn column, GameRow row) {
        board.takeSquare(Player.X, Square.LEFT_BOTTOM);
    }

    public void placeOOn(GameColumn column, GameRow row) {
    }
}
