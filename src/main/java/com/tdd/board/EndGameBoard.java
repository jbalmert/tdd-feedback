package com.tdd.board;

public class EndGameBoard implements GameBoard{
    private GameEvents events;

    public EndGameBoard(GameEvents events) {
        this.events = events;
    }

    @Override
    public void takeSquare(Square square) {
        events.gameOver();
    }
}
