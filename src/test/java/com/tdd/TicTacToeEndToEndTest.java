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
    GameSnapshotBuilder snapshotBuilder;

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
        snapshotBuilder = GameSnapshot.newSnapshot();

        placeAndTraceX(CENTER);
        verify(display).show(snapshotBuilder.build());

        placeAndTraceO(RIGHT_TOP);
        verify(display).show(snapshotBuilder.build());

        placeAndTraceX(MIDDLE_TOP);
        verify(display).show(snapshotBuilder.build());

        placeAndTraceO(LEFT_TOP);
        verify(display).show(snapshotBuilder.build());

        placeAndTraceX(MIDDLE_BOTTOM);
        verify(display).show(snapshotBuilder.build());

        verify(display).xWins();
    }

    public void placeAndTraceX(Square square) {
        game.place(square);
        snapshotBuilder.withX(square);
    }

    public void placeAndTraceO(Square square) {
        game.place(square);
        snapshotBuilder.withO(square);
    }
}
