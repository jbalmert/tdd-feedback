package com.tdd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tdd.Square.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeEndToEndTest {

    @Mock
    private GameDisplay display;

    TicTacToe game;
    private GameBoard board = new GameBoard();
    private GameEvaluator gameEvaluator = new GameEvaluator();
    private PlayerTracker playerTracker = new PlayerTracker();

    @Before
    public void configureGame() {
        game = new TicTacToe(display, board, gameEvaluator, playerTracker);
    }

    @Test
    public void XWinsAGame() throws Exception {

        placeAndTraceX(CENTER);
        verify(display).show(board);

        placeAndTraceO(RIGHT_TOP);
        verify(display).show(board);

        placeAndTraceX(MIDDLE_TOP);
        verify(display).show(board);

        placeAndTraceO(LEFT_TOP);
        verify(display).show(board);

        placeAndTraceX(MIDDLE_BOTTOM);
        verify(display).show(board);

        verify(display).xWins();
    }

    public void placeAndTraceX(Square square) {
        game.place(square);
    }

    public void placeAndTraceO(Square square) {
        game.place(square);
    }
}
