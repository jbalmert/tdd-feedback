package com.tdd.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GameStateAwareGameBoardTest {

    @Mock private PlayingGameBoard playingGameBoard;
    @Mock private EndGameBoard endGameBoard;

    @Test
    public void delegatesToPlayingGameBoardByDefault() {
        GameStateAwareGameBoard board = new GameStateAwareGameBoard(playingGameBoard, endGameBoard);

        board.takeSquare(Square.CENTER);

        verify(playingGameBoard).takeSquare(Square.CENTER);
    }

    @Test
    public void switchesPermanentlyToEndGameBoardWhenGameEndedEventReceived() {
        GameStateAwareGameBoard board = new GameStateAwareGameBoard(playingGameBoard, endGameBoard);
        board.gameEnded();

        board.takeSquare(Square.CENTER);

        verify(endGameBoard).takeSquare(Square.CENTER);
    }

}