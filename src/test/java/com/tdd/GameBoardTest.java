package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.tdd.player.Player;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class GameBoardTest {

    @Mock private GameEvaluator gameEvaluator;

    @Test
    public void boardGameIsCurrentPlayerAware() throws Exception {
        GameBoard board = new GameBoard(gameEvaluator);

        assertThat(board).isInstanceOf(CurrentPlayerAware.class);
    }

    @Test
    public void sendsMovesForPlayerXToEvaluatorAfterPlayerXTakesASquare() throws Exception {
        ArgumentCaptor<Set> captor = ArgumentCaptor.forClass(Set.class);
        GameBoard board = new GameBoard(gameEvaluator);

        board.currentPlayer(Player.X);
        board.takeSquare(Square.LEFT_BOTTOM);

        verify(gameEvaluator).evaluate(captor.capture());
        Set<Square> result = captor.getValue();
        assertThat(result).containsExactly(Square.LEFT_BOTTOM);
    }

    @Test
    public void sendsMovesForPlayerOToEvaluatorAfterPlayerOTakesASquare() throws Exception {
        ArgumentCaptor<Set> captor = ArgumentCaptor.forClass(Set.class);
        GameBoard board = new GameBoard(gameEvaluator);

        board.currentPlayer(Player.X);
        board.takeSquare(Square.LEFT_BOTTOM);
        board.currentPlayer(Player.O);
        board.takeSquare(Square.CENTER);

        verify(gameEvaluator, times(2)).evaluate(captor.capture());
        Set<Square> result = captor.getAllValues().get(1);
        assertThat(result).containsExactly(Square.CENTER);
    }
}