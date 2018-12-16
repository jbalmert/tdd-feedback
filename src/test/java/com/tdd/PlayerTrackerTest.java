package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PlayerTrackerTest {

    @Mock
    private CurrentPlayerAware currentPlayerAware;
    PlayerTracker tracker = new PlayerTracker();

    @Test
    public void initiallySignalsCurrentPlayerIsX() throws Exception {
        tracker.register(currentPlayerAware);

        tracker.nextTurn();

        verify(currentPlayerAware).currentPlayer(Player.X);
    }

    @Test
    public void altenatesBetweenPlayersWithEachCallToNextTurn() throws Exception {
        InOrder inOrder = inOrder(currentPlayerAware);
        tracker.register(currentPlayerAware);

        tracker.nextTurn();
        tracker.nextTurn();
        tracker.nextTurn();


        inOrder.verify(currentPlayerAware).currentPlayer(Player.X);
        inOrder.verify(currentPlayerAware).currentPlayer(Player.O);
        inOrder.verify(currentPlayerAware).currentPlayer(Player.X);
    }
}