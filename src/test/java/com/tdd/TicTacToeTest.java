package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.tdd.player.PlayerTracker;
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
    @Mock private PlayerTracker playerTracker;
    private TicTacToe game;

    @Before
    public void configureGame() {
        game = new TicTacToe(display, board, gameEvaluator, playerTracker);
    }

    @Test
    public void signalsStartOfNewTurn() throws Exception {
        game.place(Square.LEFT_BOTTOM);

        verify(playerTracker).nextTurn();
    }

    @Test
    public void storesMoveOnBoard() throws Exception {
        game.place(Square.LEFT_BOTTOM);

        verify(board).takeSquare(Square.LEFT_BOTTOM);
    }

    @Test
    public void evaluatesGameStateAfterMove() throws Exception {
        game.place(Square.CENTER);

        verify(board).evaluateState(gameEvaluator);
    }

    @Test
    public void displaysCurrentBoard() throws Exception {
        game.place(Square.CENTER);

        verify(board).display(display);
    }
}