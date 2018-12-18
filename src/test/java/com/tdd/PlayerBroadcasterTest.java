package com.tdd;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class PlayerBroadcasterTest {

    @Mock private CurrentPlayerAware listener1;
    @Mock private CurrentPlayerAware listener2;

    @Test
    public void sendsCurrentPlayerToAllListeners() throws Exception {
        PlayerBroadcaster broadcaster = new PlayerBroadcaster();
        broadcaster.register(listener1);
        broadcaster.register(listener2);

        broadcaster.broadcastCurrentPlayer(Player.X);

        verify(listener1).currentPlayer(Player.X);
        verify(listener2).currentPlayer(Player.X);
    }
}