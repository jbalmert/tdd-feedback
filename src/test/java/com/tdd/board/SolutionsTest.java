package com.tdd.board;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tdd.board.Square.*;
import java.util.HashSet;
import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class SolutionsTest {

    Set<Solution> solutionSet = new HashSet<>();
    @Mock Solution solution1;
    @Mock Solution solution2;

    @Before
    public void configureSolutions() {
        solutionSet.add(solution1);
        solutionSet.add(solution2);
    }

    @Test
    public void returnsTrueIfAnySolutionMatches() throws Exception {
        when(solution1.matches(anySetOf(Square.class))).thenReturn(false);
        when(solution2.matches(anySetOf(Square.class))).thenReturn(true);
        Solutions solutions = new Solutions(solutionSet);

        boolean result = solutions.matches(setOf(CENTER, MIDDLE_TOP, MIDDLE_BOTTOM));

        assertThat(result).isTrue();
    }

    @Test
    public void returnsFalseIfNoSolutionsMatch() throws Exception {
        when(solution1.matches(anySetOf(Square.class))).thenReturn(false);
        when(solution2.matches(anySetOf(Square.class))).thenReturn(false);
        Solutions solutions = new Solutions(solutionSet);

        boolean result = solutions.matches(setOf(CENTER, MIDDLE_TOP, MIDDLE_BOTTOM));

        assertThat(result).isFalse();
    }

    private Set<Square> setOf(Square... squares) {
        Set<Square> squareSet = new HashSet<>();
        for (Square square: squares) {
            squareSet.add(square);
        }
        return squareSet;
    }
}