package com.tdd.board;

import com.tdd.player.CurrentPlayerAware;
import com.tdd.player.Player;

public class GameBoardValidation implements GameBoard, CurrentPlayerAware {

    private MoveValidator moveValidator;
    private GameBoard delegate;
    private GameEvents events;
    private Player currentPlayer;

    public GameBoardValidation(MoveValidator moveValidator, GameBoard delegate, GameEvents events) {
        this.moveValidator = moveValidator;
        this.delegate = delegate;
        this.events = events;
    }

    @Override
    public void takeSquare(Square square) {
        if(moveValidator.isValid(square)){
            delegate.takeSquare(square);
        } else {
            events.invalidMove(currentPlayer, square);
        }
    }

    @Override
    public void currentPlayer(Player player) {
        currentPlayer = player;
    }
}
