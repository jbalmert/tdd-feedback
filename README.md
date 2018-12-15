## Overview 
For TicTacToe, the game takes place on a 3x3 grid.  Two players, X and O, alternate turns marking squares on the grid, starting with X.  The first player to mark
three adjacent squares (horizontally, vertically, or diagonally) wins.  If all squares are marked without anyone winning, the game is a draw.

### Initial design thought
This is a library to provide a sipmle interface to play a game of TicTacToe.  The entry point to the code will be the TicTacToe object.  It will provide methods to make moves for X and O.  After each move, a representation of the current state of the game will be sent to a GameDisplay interface.  The client is expected to provide an implementation of this class to handle events.  The TicTacToe game will correctly detect when a player has won or if a game has ended in a draw.

## Scenario 1
**XWinsAGame** 
The easiest scenario in a real game is X winning in 3 moves.  To express taking a square in the game, I introduced the placeXOn() and placeOOn() methods to the TicTacToe object.  I also introduced the GameDisplay as a dependency of the TicTacToe object.  After each move, expectations that the current state of the game is sent to the display are verified.  The GameDisplay is an interface, and mocked out as it represents the boundary of our system.  To make it easier to capture the state of the game to be sent to the display, I came up with concept of the GameSnapshot.  To make it easier to verify the state of the game, I created the GameSnapshotBuilder.  Finally, after X makes it third move, I verified that the display is told to declare X the winner.

####Thoughts and Feedback before implementation (red phase)
Just being able to express the test scenario has forced several decisions.  I'm not sure I like all of them (already).  The TicTacToe object currently exposes playXOn() and playOOn().  This allows the moves to be communicated properly, but also allows the client to send moves in the wrong order.  The design implicitly puts the responsibility of tracking whose turn it is on the client.  I want to change that in the future.  I'll make note of it for now.

### Unit test of TicTacToe
The TicTacToe game needs to accept input from the client, store the moves, evaluate the state of the game, and send state updates to the display.  That is too much responsibility for a single class, and I'm not certain how all of the responsibilites should interact just yet.  I'm making a best guess to start.  The feedback from the ensuing tests should give me feedback to correct the design as I go.  To start, I'm going to assume we need a place to store the moves.  Conceptually, we think of this as a board, so I'm going to create a Board object.  In addition, to handle understanding the game state, I'm going to create a GameEvaluator.  We already have an abstraction for the display, so I will leverage it.

**storesMoveOnBoard**
I wrote my first unit test to drive out how TicTacToe should interact with Board.  In doing so, I came up with a better metaphor for expressing what move is being made:  the Square.  It is an enum that allows each square to be uniquely named so that a column and row aren't needed.  I don't want an inconsistent set of ways to assign positions on the board, so I'm going to get rid of the concept of GameColumn and GameRow.  I also introduced the Player enum to represent X and O in the model.

