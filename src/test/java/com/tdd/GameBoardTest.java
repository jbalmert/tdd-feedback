package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.tdd.player.Player;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class GameBoardTest {

    @Mock private Squares xSquares;
    @Mock private Squares oSquares;

    @Test
    public void addsSquareToXSquaresWhenCurrentPlayerIsX() throws Exception {
        GameBoard board = new GameBoard(xSquares, oSquares);
        board.currentPlayer(Player.X);

        board.takeSquare(Square.CENTER);

        verify(xSquares).add(Square.CENTER);
    }

    @Test
    public void addsSquareToOSquaresWhenCurrentPlayerIsO() throws Exception {
        GameBoard board = new GameBoard(xSquares, oSquares);
        board.currentPlayer(Player.O);

        board.takeSquare(Square.CENTER);

        verify(oSquares).add(Square.CENTER);
    }
}