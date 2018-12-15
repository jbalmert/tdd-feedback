package com.tdd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tdd.GameRow.*;
import static com.tdd.GameColumn.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeEndToEndTest {

    @Mock
    private GameDisplay display;
    GameSnapshotBuilder snapshotBuilder;

    TicTacToe game;

    @Before
    public void configureGame() {
        game = new TicTacToe(display);
    }

    @Test
    public void XWinsAGame() throws Exception {
        snapshotBuilder = GameSnapshot.newSnapshot();

        placeAndTraceX(MIDDLE, CENTER);
        verify(display).show(snapshotBuilder.build());

        placeAndTraceO(RIGHT, TOP);
        verify(display).show(snapshotBuilder.build());

        placeAndTraceX(MIDDLE, TOP);
        verify(display).show(snapshotBuilder.build());

        placeAndTraceO(LEFT, TOP);
        verify(display).show(snapshotBuilder.build());

        placeAndTraceX(MIDDLE, BOTTOM);
        verify(display).show(snapshotBuilder.build());

        verify(display).xWins();
    }

    public void placeAndTraceX(GameColumn column, GameRow row) {
        game.placeXOn(column, row);
        snapshotBuilder.withX(column, row);
    }

    public void placeAndTraceO(GameColumn column, GameRow row) {
        game.placeOOn(column, row);
        snapshotBuilder.withO(column, row);
    }
}
