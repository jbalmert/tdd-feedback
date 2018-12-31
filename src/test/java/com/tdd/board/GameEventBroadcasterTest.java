package com.tdd.board;

import com.tdd.player.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameEventBroadcasterTest {

    @Mock private GameEvents gameEvents1;
    @Mock private GameEvents gameEvents2;

    GameEventBroadcaster broadcaster = new GameEventBroadcaster();

    @Test
    public void sendsEventToEachRegisteredListener() {
        broadcaster.register(gameEvents1);
        broadcaster.register(gameEvents2);

        broadcaster.move(Player.X, Square.CENTER);
        broadcaster.draw();
        broadcaster.wins(Player.O);
        broadcaster.invalidMove(Player.X, Square.CENTER);
        broadcaster.gameOver();

        verify(gameEvents1).move(Player.X, Square.CENTER);
        verify(gameEvents1).draw();
        verify(gameEvents1).wins(Player.O);
        verify(gameEvents1).invalidMove(Player.X, Square.CENTER);
        verify(gameEvents1).gameOver();
        verify(gameEvents2).move(Player.X, Square.CENTER);
        verify(gameEvents2).draw();
        verify(gameEvents2).wins(Player.O);
        verify(gameEvents2).invalidMove(Player.X, Square.CENTER);
        verify(gameEvents2).gameOver();
    }
}