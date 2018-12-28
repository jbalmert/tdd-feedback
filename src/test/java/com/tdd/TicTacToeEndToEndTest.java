package com.tdd;

import com.tdd.player.PlayerBroadcaster;
import com.tdd.player.PlayerToggle;
import com.tdd.player.PlayerTracker;
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
    private GameEvaluator gameEvaluator = new GameEvaluator();
    private Squares xSquares = new Squares(gameEvaluator);
    private Squares oSquares = new Squares(gameEvaluator);
    private GameBoard board = new GameBoard(xSquares, oSquares);
    private PlayerToggle playerToggle = new PlayerToggle();
    private PlayerBroadcaster playerBroadcaster = new PlayerBroadcaster();
    private PlayerTracker playerTracker = new PlayerTracker(playerToggle, playerBroadcaster);

    @Before
    public void configureGame() {
        playerBroadcaster.register(board);
        game = new TicTacToe(board, playerTracker);
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
