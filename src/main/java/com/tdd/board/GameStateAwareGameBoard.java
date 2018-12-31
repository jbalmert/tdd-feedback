package com.tdd.board;

public class GameStateAwareGameBoard implements GameStateAware, GameBoard {
    GameBoard currentBoard;
    GameBoard endGameBoard;
    public GameStateAwareGameBoard(GameBoard playingGameBoard, EndGameBoard endGameBoard) {
        this.currentBoard = playingGameBoard;
        this.endGameBoard = endGameBoard;
    }

    @Override
    public void gameEnded() {
        currentBoard = endGameBoard;
    }

    @Override
    public void takeSquare(Square square) {
        currentBoard.takeSquare(square);
    }
}
