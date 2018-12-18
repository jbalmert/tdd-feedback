package com.tdd.player;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.tdd.player.Player;
import com.tdd.player.PlayerBroadcaster;
import com.tdd.player.PlayerToggle;
import com.tdd.player.PlayerTracker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PlayerTrackerTest {

    @Mock private PlayerToggle playerToggle;
    @Mock private PlayerBroadcaster playerBroadcaster;
    PlayerTracker tracker;

    @Before
    public void configurePlayerTracker() {
        tracker = new PlayerTracker(playerToggle, playerBroadcaster);
    }

    @Test
    public void getsNextPlayerFromPlayerToggle() throws Exception {
        tracker.nextTurn();

        verify(playerToggle).nextPlayer();
    }

    @Test
    public void sendsNextPlayerToPlayerBroadcaster() throws Exception {
        when(playerToggle.nextPlayer()).thenReturn(Player.X);

        tracker.nextTurn();

        verify(playerBroadcaster).broadcastCurrentPlayer(Player.X);
    }
}