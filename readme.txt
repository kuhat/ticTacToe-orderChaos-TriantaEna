# CS611-<Assignment 2>
## <Cards Anyone?>
---------------------------------------------------------------------------
<Wenhao Zhou>
<whzhou@bu.edu>
<U36389876>

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
the behaviors and methods of a TicTacToe Player (CW1)

src/com/TriantaEna/tictactoe/TicTacToeBoardRunner.java: An actual running instance of TicTacToe game which implements
interface GameState, which contains customized methods and behaviors (CW1)

src/com/TriantaEna/orderAndChaos/orderAndChaosRunner.java: Order And Chaos player inherited from the Player class,
which contains the behaviors and methods of an Order And Chaos Player (CW1)

src/com/TriantaEna/orderAndChaos/orderAndChaosBoardPlayer.java: An actual running instance of Order And Chaos game which
implements interface GameState, which contains customized methods and behaviors (CW1)

## Notes
---------------------------------------------------------------------------
1. <Files to be parsed should be stored in ConfigFiles, for parser class to
read class>
2. <Notes to grader>: TriantaEna in the CW2 are added in the previous structure of CW1. The overall structure of the original
files in CW1 are changed to make the project more loosely-coupled.

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "/TriantaEna" after unzipping the files
2. Run the following instructions:

javac -d bin src/com/TriantaEna/*.java src/com/TriantaEna/utils/boardGame/*.java src/com/TriantaEna/utils/cardGame/*.java src/com/TriantaEna/utils/*.java src/com/TriantaEna/TriantaEna/*.java src/com/TriantaEna/orderAndChaos/*.java src/com/TriantaEna/tictactoe/*.java
java -cp bin com.TriantaEna.main

## Input/Output Example
---------------------------------------------------------------------------
Welcome to TicTacToe && Order and Chaos && TriantaEna!
Please enter the game number you want to play
 1 for TicTacToe,
 2 for Order and Chaos,
 3 for Trianta Ena:
input:
3
output:
You chose to play TriantaEna!
++++++++++++ Welcome to the special edition of Black Jack ++++++++++++++
+++++++++++++++++++++ ----- Trianta Ena ----- ++++++++++++++++++++++++++
 This is a terminal based Casino game, pay attention to the output of terminal:
You will be handed a standard card with value numbers from 2 to 10 and J, Q, K, A
The Face cards J Q K are of 10 value each and A can be either 1 or 11.
However, if there are more than one A in your hands, only one can be considered as 1.
*++++++++++++++++++++++*++  Now Let's begin  +++++++++++++++++++*********

Please enter the number of players (2 ~ 7):
input:
3
output:
You chose a 3 player game!
Player 0 will be the dealer (Bank), please enter the name for dealer:
input:
dealerUpUpUp
output:
Please enter the name for player 1 :
input:
1p
output:
Please enter the name for player 2 :
input:
2p
output:
The balance for each player is $100, and the balance for the dealer dealerUpUpUp is $300.
 Now, let's begin.
start
---------------------++++++++ Round 1++++++++-----------------------
Player 1p your current hand card are: (Clubs, 5)
Your total value is: 5
Deal's visible cards are: (Diamonds, 2)
Player 1p your current balance is $100
Please enter an Integer between 1 and 100 to bet.
 Type 0 to fold.
input:
10
output:
Player 1p you chose to bet.
***** Your current balance is $90 after this bet. *****
______-----------_________-----------_________---------
Player 2p your current hand card are: (Diamonds, 4)
Your total value is: 4
Deal's visible cards are: (Diamonds, 2)
Player 2p your current balance is $100
Please enter an Integer between 1 and 100 to bet.
 Type 0 to fold.
input:
20
output:
Player 2p you chose to bet.
***** Your current balance is $80 after this bet. *****
______-----------_________-----------_________---------
============ Player 1p This is your round! ============
Deal's visible cards are: (Diamonds, 2)(Diamonds, 8)(spades, 9)
Player 1p your current hand card are: (Clubs, 5)(Clubs, 10)(Clubs, 2)
Your total value is: 17
Player 1p, your total balance is: $90
Your current bet for this round is: 10
Please select your next action:
 1 for hit, 0 for stand
input:
1
output:
You chose to hit
Player 1p your current hand card are: (Clubs, 5)(Clubs, 10)(Clubs, 2)(Hearts, 8)
Your total value is: 25
Please select your next action:
 1 for hit, 0 for stand
input:
1
output:
You chose to hit
Player 1p your current hand card are: (Clubs, 5)(Clubs, 10)(Clubs, 2)(Hearts, 8)(Diamonds, 5)
Your total value is: 30
Please select your next action:
 1 for hit, 0 for stand
input:
0
output:
You chose to stand
No action needed, your balance dose not change.
Player 1p your current hand card are: (Clubs, 5)(Clubs, 10)(Clubs, 2)(Hearts, 8)(Diamonds, 5)
Your total value is: 30
============ Player 2p This is your round! ============
Deal's visible cards are: (Diamonds, 2)(Diamonds, 8)(spades, 9)
Player 2p your current hand card are: (Diamonds, 4)(Diamonds, 3)(Hearts, 4)
Your total value is: 11
Player 2p, your total balance is: $80
Your current bet for this round is: 20
Please select your next action:
 1 for hit, 0 for stand
input:
11
output:
Invalid input. Please enter 0 for stand, 1 for hit!
Please select your next action:
 1 for hit, 0 for stand
input:
1
output:
You chose to hit
Player 2p your current hand card are: (Diamonds, 4)(Diamonds, 3)(Hearts, 4)(spades, 4)
Your total value is: 15
Please select your next action:
 1 for hit, 0 for stand
input:
1
output:
You chose to hit
Player 2p your current hand card are: (Diamonds, 4)(Diamonds, 3)(Hearts, 4)(spades, 4)(Hearts, 7)
Your total value is: 22
Please select your next action:
 1 for hit, 0 for stand
input:
1
output:
You chose to hit
Player 2p your current hand card are: (Diamonds, 4)(Diamonds, 3)(Hearts, 4)(spades, 4)(Hearts, 7)(Clubs, 4)
Your total value is: 26
Please select your next action:
 1 for hit, 0 for stand
input:
1
output:
You chose to hit
Player 2p your current hand card are: (Diamonds, 4)(Diamonds, 3)(Hearts, 4)(spades, 4)(Hearts, 7)(Clubs, 4)(Diamonds, Q)
Your total value is: 36
Player 2p, your hand is bust......
 your current value is 36
___________
Players' rounds end
____________
---------------------- Dealer's show time ---------------------
Dealer dealerUpUpUp, your current hand cards are: (Diamonds, 2)(Diamonds, 8)(spades, 9)
Your total value is: 19
Dealer Hits.....
Dealer dealerUpUpUp, your current hand cards are: (Diamonds, 2)(Diamonds, 8)(spades, 9)(Hearts, Q)
Your total value is: 29
-----------------------
Deal's round ends
____________________
Round1, Player 1p wins $10.
Player 1p, your total balance is: $110
Player 1p, do you want to cash out?
 0 for no, 1 for yes
input:
0
output:
Round 1, player 2p loses $-20.
Player 2p, your total balance is: $80
Player 2p, do you want to cash out?
 0 for no, 1 for yes
input:
1
output:
Player leaves this game with cash: $80
At round 1 dealer hava got balance for $310
---------------------++++++++ Round 2++++++++-----------------------
Player 1p your current hand card are: (spades, 9)
Your total value is: 9
Deal's visible cards are: (Clubs, 7)
Player 1p your current balance is $110
Please enter an Integer between 1 and 110 to bet.
 Type 0 to fold.
input:
50
output:
Player 1p you chose to bet.
***** Your current balance is $60 after this bet. *****
______-----------_________-----------_________---------
============ Player 1p This is your round! ============
Deal's visible cards are: (Clubs, 7)(Hearts, 3)(Diamonds, A)
Player 1p your current hand card are: (spades, 9)(Hearts, A)(Diamonds, J)
Your total value is: 20
Player 1p, your total balance is: $60
Your current bet for this round is: 50
Please select your next action:
 1 for hit, 0 for stand
input:
1
output:
You chose to hit
Player 1p your current hand card are: (spades, 9)(Hearts, A)(Diamonds, J)(Diamonds, K)
Your total value is: 30
Please select your next action:
 1 for hit, 0 for stand
input:
0
output:
You chose to stand
No action needed, your balance dose not change.
Player 1p your current hand card are: (spades, 9)(Hearts, A)(Diamonds, J)(Diamonds, K)
Your total value is: 30
___________
Players' rounds end
____________
---------------------- Dealer's show time ---------------------
Dealer dealerUpUpUp, your current hand cards are: (Clubs, 7)(Hearts, 3)(Diamonds, A)
Your total value is: 11
Dealer Hits.....
Dealer dealerUpUpUp, your current hand cards are: (Clubs, 7)(Hearts, 3)(Diamonds, A)(Diamonds, Q)
Your total value is: 21
Dealer Hits.....
Dealer dealerUpUpUp, your current hand cards are: (Clubs, 7)(Hearts, 3)(Diamonds, A)(Diamonds, Q)(Clubs, Q)
Your total value is: 31
Congratulations!!! your hand forms a Trianta Ena!!!
-----------------------
Deal's round ends
____________________
Round 2, player 1p loses $-50.
Player 1p, your total balance is: $60
Player 1p, do you want to cash out?
 0 for no, 1 for yes
input:
1
output:
Player leaves this game with cash: $60
At round 2 dealer hava got balance for $360
Game Over, Thanks for playing Trianta Ena!!!