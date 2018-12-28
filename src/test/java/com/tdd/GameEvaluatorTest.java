package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static com.tdd.Square.*;


@RunWith(MockitoJUnitRunner.class)
public class GameEvaluatorTest {

    @Mock private GameEvents events;
    @Mock Solution solution1;
    @Mock Solution solution2;
    private Set<Solution> solutions = new HashSet<>();
    GameEvaluator evaluator;

    @Before
    public void configureEvaluator() {
        solutions.add(solution1);
        solutions.add(solution2);
        evaluator = new GameEvaluator(events, solutions);
    }

    @Test
    public void sendsXWinsIfAnySolutionMatches() throws Exception {
        when(solution1.matches(anySetOf(Square.class))).thenReturn(true);
        when(solution2.matches(anySetOf(Square.class))).thenReturn(false);

        evaluator.evaluate(setOf(MIDDLE_BOTTOM, MIDDLE_TOP, CENTER));

        verify(events).xWins();
    }

    @Test
    public void doesNothingWhenNoSolutionMatches() throws Exception {
        when(solution1.matches(anySetOf(Square.class))).thenReturn(false);
        when(solution2.matches(anySetOf(Square.class))).thenReturn(false);

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