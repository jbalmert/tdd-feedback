package com.tdd.board;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.tdd.player.Player;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class PlayingGameBoardTest {

    @Mock private Squares xSquares;
    @Mock private Squares oSquares;
    @Mock private GameEvents events;

    @Test
    public void addsSquareToXSquaresWhenCurrentPlayerIsX() throws Exception {
        PlayingGameBoard board = new PlayingGameBoard(xSquares, oSquares, events);
        board.currentPlayer(Player.X);

        board.takeSquare(Square.CENTER);

        verify(xSquares).add(Square.CENTER);
    }

    @Test
    public void addsSquareToOSquaresWhenCurrentPlayerIsO() throws Exception {
        PlayingGameBoard board = new PlayingGameBoard(xSquares, oSquares, events);
        board.currentPlayer(Player.O);

        board.takeSquare(Square.CENTER);

        verify(oSquares).add(Square.CENTER);
    }

    @Test
    public void sendsMoveEventWhenSquareIsTaken() throws Exception {
        PlayingGameBoard board = new PlayingGameBoard(xSquares, oSquares, events);
        board.currentPlayer(Player.X);

        board.takeSquare(Square.LEFT_BOTTOM);

        verify(events).move(Player.X, Square.LEFT_BOTTOM);
    }
}