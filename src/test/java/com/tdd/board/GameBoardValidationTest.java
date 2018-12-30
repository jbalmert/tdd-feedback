package com.tdd.board;

import com.tdd.player.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameBoardValidationTest {

    @Mock private GameBoard delegate;
    @Mock private MoveValidator moveValidator;
    @Mock private GameEvents events;

    @Test
    public void delegatesIfMoveIsValid() {
        GameBoardValidation validation = new GameBoardValidation(moveValidator, delegate, events);
        when(moveValidator.isValid(Square.CENTER)).thenReturn(true);

        validation.takeSquare(Square.CENTER);

        verify(delegate).takeSquare(Square.CENTER);
    }

    @Test
    public void generatesInvalidMoveEvenWhenMoveIsInvalid() {
        GameBoardValidation validation = new GameBoardValidation(moveValidator, delegate, events);
        validation.currentPlayer(Player.X);
        when(moveValidator.isValid(Square.CENTER)).thenReturn(false);

        validation.takeSquare(Square.CENTER);

        verifyZeroInteractions(delegate);
        verify(events).invalidMove(Player.X, Square.CENTER);
    }
}