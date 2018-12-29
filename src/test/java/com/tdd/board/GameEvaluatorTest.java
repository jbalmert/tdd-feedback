package com.tdd.board;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.tdd.board.*;
import com.tdd.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static com.tdd.board.Square.*;


@RunWith(MockitoJUnitRunner.class)
public class GameEvaluatorTest {

    @Mock private GameEvents events;
    @Mock
    Solutions solutions;
    @Mock
    Solution solution2;

    GameEvaluator evaluator;

    @Before
    public void configureEvaluator() {
        evaluator = new GameEvaluator(events, solutions);
    }

    @Test
    public void sendsXWinsIfAnySolutionMatches() throws Exception {
        when(solutions.matches(anySetOf(Square.class))).thenReturn(true);
        evaluator.currentPlayer(Player.X);

        evaluator.evaluate(setOf(MIDDLE_BOTTOM, MIDDLE_TOP, CENTER));

        verify(events).wins(Player.X);
    }

    @Test
    public void doesNothingWhenNoSolutionMatches() throws Exception {
        when(solutions.matches(anySetOf(Square.class))).thenReturn(false);

        evaluator.evaluate(setOf(MIDDLE_BOTTOM, MIDDLE_TOP, CENTER));

        verifyZeroInteractions(events);
    }

    private Set<Square> setOf(Square... squares) {
        Set<Square> squareSet = new HashSet<>();
        for (Square square: squares) {
            squareSet.add(square);
        }
        return squareSet;
    }
}