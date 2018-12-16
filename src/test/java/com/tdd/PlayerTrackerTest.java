package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PlayerTrackerTest {

    @Mock
    private CurrentPlayerAware currentPlayerAware;

    @Test
    public void initiallySignalsCurrentPlayerIsX() throws Exception {
        PlayerTracker tracker = new PlayerTracker();
        tracker.register(currentPlayerAware);

        tracker.nextTurn();

        verify(currentPlayerAware).currentPlayer(Player.X);
    }
}