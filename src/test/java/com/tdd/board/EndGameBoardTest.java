package com.tdd.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EndGameBoardTest {

    @Mock private GameEvents events;

    @Test
    public void onlySendsGameOverEventAndDoesNotProcessMoveRequests() {
        EndGameBoard board = new EndGameBoard(events);

        board.takeSquare(Square.CENTER);

        verify(events).gameOver();
    }
}