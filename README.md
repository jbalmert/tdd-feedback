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
The TicTacToe game needs to accept input from the client, store the moves, evaluate the state of the game, and send state updates to the display.  That is too much responsibility for a single class, and I'm not certain how all of the responsibilities should interact just yet.  I'm making a best guess to start.  The feedback from the ensuing tests should give me guidance to correct the design as I go.  To start, I'm going to assume we need a place to store the moves.  Conceptually, we think of this as a board, so I'm going to create a Board object.  In addition, to handle understanding the game state, I'm going to create a GameEvaluator.  We already have an abstraction for the display, so I will leverage it.

**storesMoveOnBoard**
I wrote my first unit test to drive out how TicTacToe should interact with Board.  In doing so, I came up with a better metaphor for expressing what move is being made:  the Square.  It is an enum that allows each square to be uniquely named so that a column and row aren't needed.  I don't want an inconsistent set of ways to assign positions on the board, so I'm going to get rid of the concept of GameColumn and GameRow.  I also introduced the Player enum to represent X and O in the model.

**evaluatesGameStateAfterMove**
With my second unit test, I'm starting to notice a few problems with my initial design.  First, It looks like almost every object in our model is going to need to know which Player made the latest move.  We can continue to pass the Player from object to object or begin exploring other ways of broadcasting which player is making the current move.  The second problem is related.  I have two methods on TicTacToe that need to do almost the exact same thing.  To fully test it will almost certainly require heavy duplication of tests with minor tweaks to specify which player is referenced.  With this feedback and the initial realization that we're potentially allowing invalid moves to be requested, it is time to explore alternatives to passing the Player around.  We need something that tracks the current player and makes that information available to any object that needs to know.  At the same time, we need to remove this responsibility from the client/API.  I'm going to introduce a PlayerTracker object to hold the responsibility of knowing which player is current.  As the current player changes, it will broadcast the new player to all interested objects.  Each interested object will have to implement PlayerNotification interface and be registerd with the PlayerTracker.

**signalsStartOfNewTurn**
With this test and the design changes to accomodate it, the overall design is simplified.  Anytime I feel like I've simplified my design or made the system easier to reason about I tend to think I'm on the right track.

**displaysCurrentBoard**
I changed one of my design decisions from the end to end test.  I think in the end that the end to end test idea of how to express game state is closer to what I want in th end, but my unit test did not have the information to meet the needs of the end to end test.  This is a strong suggestion that there are misplaced responsibilities in the decisions I have made.  I believe I have two places that will need to be addressed.  Both involve the GameBoard.  In two different methods, I'm sending the board to another object to have some operations performed on it.  These operations will most likely involve asking the board to expose some of its internal state to the game evaluator and the display object.  This can be done (and frequently is) but it leads to tightly coupled objects.  It would be harder to change the implementation of the Board object without risking breaking the implementations of upstream objects.  To avoid this, I need to move the operations on the data as close to the data as possible.  That means inverting the relationship of these objects and allow the Board to send information to the GameEvaluator and the GameDisplay.  I'm going to make this change next to avoid complications downstream.

### Unit test of PlayerTracker
The PlayerTracker is responsible for keeping track of who the current player is and updating other interested objects when that changes.

**initiallySignalsCurrentPlayerIsX**
I introduced the ability to register with the PlayerTracker.  This will be my mechanism to keep all objects that need to be aware of the current player in sync.  I don't have much feedback from this test.  The design as far as it has been implemented seems to be moving in the right direction.

**altenatesBetweenPlayersWithEachCallToNextTurn**
I had to use mockito Inorder to prove that the Player toggles between X and O on each call.  Even though it's only 2 lines long, the nextTurn() method is operating at two different levels of abstraction.  The first line is calling a dependency while the second line calls a method to express the intent of the method.  I'll clean this up during the next test since I'll be modifying it there anyway.

**sendsCurrentPlayerToAllRegisteredObjects**
I cleaned up the nextTurn method.  Now both lines call descriptive methods.  However, even with the method cleaned up, I now have this class handling two distinct responsibilities:  notifying listeners of current player and toggling the current player.  It's not a huge added complexity, but it is something that should be addressed.  I'm going to extract those responsibilities and let the PlayerTracker collaborate with them.

### Unit test of PlayerTracker (second try)
I wiped out the implementation and tests for the PlayerTracker and will try again.

I quickly reimplemented the PlayerTracker.  This time I introduced the PlayerToggle and PlayerBroadcaster abstractions to handle the responsibilities the PlayerTracker handled all by itself in the previous implementation.  The resulting code is kept at a single level of abstraction.  This is complete overkill for Tic Tac Toe where the requirements aren't going to change.  But in an enterprise app, where requirements are going to change in unexpected ways, this level of decomposition is absolutely worth the effort.

### Unit test of PlayerToggle
The PlayerToggle will toggle between X and O on each call to nextPlayer().  The first call will return X.

**returnsPlayerXOnFirstCall** Simple to implement.  Just return Player X.

**togglesBetweenXAndOWithEachCall**  I now need to track the current player and update it with each call.  I created calcuateNextPlayer() to handle the details.  Then I returned the new currentPlayer.  The iplementation feels simple and clean, so I don't feel any need to change the code further.

### Unit test of PlayerBroadcaster
the PlayerBroadcaster will send the next currentPlayer to all registered listeners.

**sendsCurrentPlayerToAllListeners**  I created the register method to add listeners.  The implementation of broadcastCurrentPlayer() loops over all the registered listeners and sends the current player. This implementation is fairly straightforward, so I don't see any need to change the code further.

### Organizing code
I moved all the Player classes into a player package to keep the number of classes in one spot from becoming overwhelming.

## Unit test of GameBoard
The GameBoard is responsible for tracking moves.  I've already stubbed out three methods:  takeSquare(), evaluateState(), display().  It feels a bit off to need three messages to accomplish a task.  This suggests some responsibilities may be a little bit off.  I'll keep a lookout for trouble...

**tracksSquaresTakenByPlayer**  Right off the bat, testing the takeSquare() method is difficult.  I'm stuck.  I want to assert/verify that when a square is taken, the GameBoard tracks it.  But, in isolation, this method only takes information in.  It does not send out any other messages.  My options are to expose the underlying storage mechanism for the GameBoard or rethink how the GameBaord works.  I am never happy with exposing data only for the purpose of testing.  So that leaves rethinking the GameBoard.
I took some time to analyze the calls made to the board.  The board should be involved with all the responsibilities that have been stubbed out.  The thing that I missed is that when a square is taken, it should always be followed by evaluating the state of the game, and then some sort of message is sent to the display.  Since all of these things should happen in sequence together, it would make sense for them to be coordinated by one object.  Technically, that is what I have right now, but for each call in the sequence, it defers to the GameBoard.  This strongly suggests the responsibility belongs to the GameBoard itself, not the top level TicTacToe game.  This kind of subtle incorrect assignment of responsibilities is quite common while test driving code.  Frequently, the answer is to push the responsibility down to subordinate objects.  I'm going to move evaluateState() and display() within takeSquare() and will evaluate again. 

**boardGameIsCurrentPlayerAware**  BoardGame needs to know the current Player as it changes, so it must implement the CurrentPlayerAware interface.

**sendsMovesForPlayerXToEvaluatorAfterPlayerXTakesASquare** I initially tried to reintroduce the GameSnapshot, but it added too much noise to the test.  The implementation is getting a bit messy as I'm trying to figure out how to store the state of the game.  It occurred to me that when evaluating the game state, only the Squares taken by the current Player are relevant.  So, it made sense to separate X moves from O moves into separate Sets.  There may be an abstraction I'm missing that could simplify my model.  I will think this over.

**sendsMovesForPlayerOToEvaluatorAfterPlayerOTakesASquare** This test exists to force a different set of moves to be sent to the GameEvaluator depening on the Player.


## Unit test of GameBaord (second try)
The implementation of GameBoard was tracking state by creating a Map of Player -> Set<Square> to track each Players moves individually.  This worked, but created a bit of noise both in the test and the implementation.  There were simply too many parts in play.  While it may not have seemed like too much, the implementation obscured the intent slightly by getting bogged down in details.  There are few things about this that told me there is an abstraction I was missing.  First, Collections (Maps, Lists, Sets) are not particularly clean when used as domain concepts.  That is, Collections are designed to be extremely efficient at storing and manipulating related groups of data.  They are not great at encapsulating domain concepts.  This doesn't mean that Collections are inappropriate to use.  On the contrary, they are extremely useful.  However, they should be hidden by a higher level object that represents the domain concept and leverages a Collection without exposing all of it's functionality.  Second, I had multiple objects assembled to represent to the concept of game moves (the Map of two Sets<Square).  It is more convenient to bundle these related objects together and allow a domain object to mange the interactions with the underlying implementation.  So, I introduced the GameMoves object.  I then realized that the GameMoves object needs to know the current player, not the GameBoard.  So, I removed the dependency on the CurrentPlayerAware interface.  The resulting implementation is looking much simpler.
 
## Unit test of GameBoard (third try)
I pushed down too much of the responsibility of the GameBoard to the GameMoves object.  It's hard to justify the existence of GameBoard in this state.  I'm running into a similar problem from before where responsibilities are misplaced.  I should have to tell the GameMoves object to evaluate the game state after adding a new square.  I'm going to back up and find a different point to separate the responsibilities.

The new implementation looks like it hits the right balance point.  The GameBoard's responsibility it to route a players move to the correct collection of Squares.  It has to be CurrentPlayerAware again, but it does not own the responsibility of evaluating player moves.  That will be pushed down to the Squares object.

## Unit test of Squares
The Squares object is responsible for holding onto Square objects for a Player and evaluating the state of the game after a new Square is added.

### GameDisplay revisted
In my end to end test I have several verifications of the GameDisplay being told to show the board.  This is a little presumptuous and is rooted my original thought of implementing the app as a command line interactive application.  Now that it is simply a library to play a game of TicTacToe it makes sense to think of the messages being sent back to the client as events rather than display commands.  I don't like the idea of sharing an internal domain concept with the client.  It would give them the option to interact with system inappropriately.  I think for now I will simply send an event specifying a player taking a square.  Later when we get to error checking, I may also send an event with remaining valid squares as the same time.

## Updating TicTacToeEndToEndTest
**XWinsAGame** 
I need to drive out the expected behaviors of the events.  I am rebuilding this test to hopefully be more readable.

## Unit test of GameBoard (just adding the move event)
As each move is made, the GameBoard now sends a move event to the client.

## TicTacToeEndToEndTest
I just made significant progress towards finishing the first end to end test.  When I run the test instead of failing at the first event verification, I am now failing at the last one: XWins.  To make that work I need to implement the game evaluation functionality.

## GameEvaluator
After each move, the evaluator will receive a set of all the moves made by the current player, including the latest move.  It is the job of the Evaluator to determine if a subset of the squares matches a winning solution, an XWins, OWins, or draw event should be fired off if warranted.  Otherwise, it does nothing.  The end to end test only defines the need for the Xwins event, so I will limit the implementation to that until another scenario forces me to add more.  To simplify my life, it is important to note that there are only 8 valid winning solutions in classic TicTacToe: 3 columns, 3 rows, 2 diagonals.  I can model these solutions directly.  If the game were designed to be a 4x4 or 5x5 grid, I would consider dynamically permuting all the solutions rather than hard coding them.
