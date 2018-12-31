package com.tdd.board;

import com.tdd.player.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EndGameListenerTest {

    @Mock private GameStateAware gameStateAware;

    @Test
    public void tellsStateAwareGameBoardGameHasEndedWhenAPlayerWins() {
        EndGameListener listener = new EndGameListener(gameStateAware);

        listener.wins(Player.X);

        verify(gameStateAware).gameEnded();
    }
}