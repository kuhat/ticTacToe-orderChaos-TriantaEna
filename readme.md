# Cards and Boards game infrustructure
---------------------------------------------------------------------------
An OOP design in Java

## Files
---------------------------------------------------------------------------
<A brief description of each file and what it does>

src/com/TriantaEna/main.java: Main entrance of this application

src/com/TriantaEna/Game.java: game selector and game starter

src/com/TriantaEna/utils/GameState.java: GameState interface which provides common states such as run, init, start, isover

src/com/TriantaEna/utils/Player.java: Parent player interface which defines the behavior of a general player

src/com/TriantaEna/utils/cardGame/Card.java: An actual card object used in TriantaEna

src/com/TriantaEna/utils/cardGame/Deck.java: A deck object which represent the real deck with 52 cards

src/com/TriantaEna/utils/cardGame/cardPlayer.java: A card player object which implements Player interface

src/com/TriantaEna/utils/cardGame/Hand.java: A hand object representing the hand of card currently owned by the card player

src/com/TriantaEna/TriantaEna/triantaEnaChecker.java: A checker class which defines conditions to win or lose in a TriantaEna game

src/com/TriantaEna/TriantaEna/triantaEnaInstructions.java: A class containing instructions used in the TriantaEna game

src/com/TriantaEna/TriantaEna/triantaEnaPlayer.java: A TriantaEna game Player implementing the general player interface

src/com/TriantaEna/TriantaEna/triantaEnaRunner.java: A TriantaEna game instance which contains the main workflows of a TriantaEna game

src/com/TriantaEna/utils/boardGame/Board.java: Board class used by TicTacToe and OrderAndChaos (CW1)

src/com/TriantaEna/utils/boardGame/Cell.java: Cell class used by the two board games in CW1, a single cell on a board (CW1)

src/com/TriantaEna/utils/boardGame/boardPlayer.java: board game Player abstract class which defines the basic behavior of a board game player (CW1)

src/com/TriantaEna/tictactoe/TicTacToeBoardPlayer.java: TicTacToe player inherited from the Player class, which contains
the behaviors and methods of a TicTacToe Player

src/com/TriantaEna/tictactoe/TicTacToeBoardRunner.java: An actual running instance of TicTacToe game which implements
interface GameState, which contains customized methods and behaviors 

src/com/TriantaEna/orderAndChaos/orderAndChaosRunner.java: Order And Chaos player inherited from the Player class,
which contains the behaviors and methods of an Order And Chaos Player 

src/com/TriantaEna/orderAndChaos/orderAndChaosBoardPlayer.java: An actual running instance of Order And Chaos game which
implements interface GameState, which contains customized methods and behaviors 

## How to compile and run
1. Navigate to the directory "/TriantaEna" after unzipping the files
2. Run the following instructions:

javac -d bin src/com/TriantaEna/*.java src/com/TriantaEna/utils/boardGame/*.java src/com/TriantaEna/utils/cardGame/*.java src/com/TriantaEna/utils/*.java src/com/TriantaEna/TriantaEna/*.java src/com/TriantaEna/orderAndChaos/*.java src/com/TriantaEna/tictactoe/*.java
java -cp bin com.TriantaEna.main
