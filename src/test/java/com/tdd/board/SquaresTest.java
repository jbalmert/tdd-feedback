package com.tdd.board;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class SquaresTest {

    @Mock private GameEvaluator gameEvaluator;

    @Test
    public void evaluatesGameStateAfterSquareIsAdded() throws Exception {
        ArgumentCaptor<Set> captor = ArgumentCaptor.forClass(Set.class);
        Squares squares = new Squares(gameEvaluator);

        squares.add(Square.CENTER);

        verify(gameEvaluator).evaluate(captor.capture());
        assertThat(captor.getValue()).containsExactly(Square.CENTER);
    }
}