package com.tdd;

import com.tdd.player.Player;
import com.tdd.player.PlayerBroadcaster;
import com.tdd.player.PlayerToggle;
import com.tdd.player.PlayerTracker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.tdd.Square.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeEndToEndTest {

    @Mock
    private GameEvents events;

    TicTacToe game;
    private GameEvaluator gameEvaluator = new GameEvaluator();
    private Squares xSquares = new Squares(gameEvaluator);
    private Squares oSquares = new Squares(gameEvaluator);
    private PlayerToggle playerToggle = new PlayerToggle();
    private PlayerBroadcaster playerBroadcaster = new PlayerBroadcaster();
    private PlayerTracker playerTracker = new PlayerTracker(playerToggle, playerBroadcaster);

    @Before
    public void configureGame() {
        GameBoard board = new GameBoard(xSquares, oSquares, events);
        playerBroadcaster.register(board);
        game = new TicTacToe(board, playerTracker);
    }

    @Test
    public void XWinsAGame() throws Exception {
        game.place(CENTER);
        game.place(RIGHT_TOP);
        game.place(MIDDLE_TOP);
        game.place(LEFT_TOP);
        game.place(MIDDLE_BOTTOM);

        InOrder inOrder = inOrder(events);
        inOrder.verify(events).move(Player.X, CENTER);
        inOrder.verify(events).move(Player.O, RIGHT_TOP);
        inOrder.verify(events).move(Player.X, MIDDLE_TOP);
        inOrder.verify(events).move(Player.O, LEFT_TOP);
        inOrder.verify(events).move(Player.X, MIDDLE_BOTTOM);
        inOrder.verify(events).xWins();
    }

}
