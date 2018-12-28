package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static com.tdd.Square.*;


@RunWith(MockitoJUnitRunner.class)
public class SolutionTest {

    @Test
    public void matchesIfSquaresContainsAllPartsOfSolution() throws Exception {
        Solution solution = new Solution(MIDDLE_BOTTOM, CENTER, MIDDLE_TOP);

        boolean result = solution.matches(setOf(MIDDLE_BOTTOM, CENTER, LEFT_MIDDLE, MIDDLE_TOP));

        assertThat(result).isTrue();
    }

    @Test
    public void doesNotMatchIfSquaresDoesNotContainAllPartsOfSolution() throws Exception {
        Solution solution = new Solution(MIDDLE_BOTTOM, CENTER, MIDDLE_TOP);

        boolean result = solution.matches(setOf(RIGHT_MIDDLE, CENTER, LEFT_MIDDLE, MIDDLE_TOP));

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