package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TicTacToeTest {

    @Mock private GameBoard board;
    @Mock private GameDisplay display;

    @Test
    public void storesMoveOnBoard() throws Exception {
        TicTacToe game = new TicTacToe(display, board);

        game.placeXOn(GameColumn.LEFT, GameRow.BOTTOM);

        verify(board).takeSquare(Player.X, Square.LEFT_BOTTOM);
    }
}