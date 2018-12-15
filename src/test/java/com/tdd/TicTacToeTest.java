package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TicTacToeTest {

    @Mock private GameBoard board;
    @Mock private GameDisplay display;
    @Mock private GameEvaluator gameEvaluator;
    private TicTacToe game;

    @Before
    public void configureGame() {
        game = new TicTacToe(display, board, gameEvaluator);
    }

    @Test
    public void storesMoveOnBoard() throws Exception {
        game.placeXOn(Square.LEFT_BOTTOM);

        verify(board).takeSquare(Player.X, Square.LEFT_BOTTOM);
    }

    @Test
    public void evaluatesGameStateAfterMove() throws Exception {
        game.placeXOn(Square.CENTER);

        verify(gameEvaluator).evaluateState(board);
    }
}