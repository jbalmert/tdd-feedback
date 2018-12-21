package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class GameBoardTest {

    @Mock private GameEvaluator gameEvaluator;
    @Mock private GameMoves moves;

    @Test
    public void addsSquareToGameMoves() throws Exception {
        GameBoard board = new GameBoard(gameEvaluator, moves);

        board.takeSquare(Square.CENTER);

        verify(moves).add(Square.CENTER);
    }

    @Test
    public void tellsMovesToEvaluateGameStateWithTheGameEvaluator() throws Exception {
        GameBoard board = new GameBoard(gameEvaluator, moves);

        board.takeSquare(Square.CENTER);

        verify(moves).evaluate(gameEvaluator);
    }
}