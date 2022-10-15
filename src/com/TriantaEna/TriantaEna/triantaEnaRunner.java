package com.TriantaEna.TriantaEna;

import com.TriantaEna.utils.GameState;
import com.TriantaEna.utils.Player;
import com.TriantaEna.utils.cardGame.Card;
import com.TriantaEna.utils.cardGame.Deck;
import com.TriantaEna.utils.cardGame.Hand;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class triantaEnaRunner implements GameState {

    private PrintStream printStream;

    private Scanner scanner;

    private final int MAX_VAL = 31;

    private final int DEALER_VAL = 27;

    private triantaEnaPlayer dealer;

    private ArrayList<triantaEnaPlayer> players;

    private static int round;

    private Deck[] decks;

    private triantaEnaChecker checker;

    public triantaEnaRunner(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.round = 1;
        decks = new Deck[2];
        decks[0] = new Deck();
        decks[1] = new Deck();
        this.dealer = null;
        this.players = null;
        checker = new triantaEnaChecker(MAX_VAL, DEALER_VAL);
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public void run(String[] startInstructions, Player[] Players, String[] chesse) {
        printStream.println("start");
        while (!players.isEmpty()) {
            playARound();
            washCard();
        }
        printStream.println("Game Over, Thanks for playing Trianta Ena!!!");
    }

    @Override
    public void start() {
        init();
        run(new String[]{}, new Player[]{}, new String[]{});
    }

    @Override
    public void init() {
        triantaEnaInstructions.initInstruction(this.printStream);
        this.printStream.println();
        initPlayer();
    }

    /**
     * A single round of game, includes firstBet, dealCard, playerRound, dealerRound, calCuRes, RotateDealer
     */
    private void playARound() {
        printStream.println("---------------------++++++++ Round " + this.round + "++++++++-----------------------");

        // Dealer deal one card to each player
        for (triantaEnaPlayer player : this.players) {
            Hand hand = player.getHand();
            // If the first deck uses up, ues the second deck
            Card newCard = decks[0].dealCard() == null ? decks[1].dealCard() : decks[0].dealCard();
            hand.addCard(newCard);
        }
        Card newCard = decks[0].dealCard() == null ? decks[1].dealCard() : decks[0].dealCard();
        this.dealer.getHand().addCard(newCard);

        // Ask players to place their bet or choose to fold without betting
        for (triantaEnaPlayer player : players) {
            triantaEnaInstructions.showInforPlayerHand(this.printStream, player);
            triantaEnaInstructions.showInforDealerVisible(this.printStream, dealer);
            firstBet(player);
        }
        dealCard();
        playerRound();
        dealerRound();
        calcuRes();
        rotateDealer();
        this.round += 1;
    }

    /**
     * Initialize the players in one game
     */
    private void initPlayer() {
        String input = "";
        boolean strResult = false;

        // Ask player to enter number of players
        while (!strResult) {
            input = getInput("Please enter the number of players (2 ~ 7):");
            strResult = (input != null && input.matches("[2-7]"));
        }
        strResult = false;
        int numberOfPlayer = Integer.valueOf(input);
        this.printStream.println("You chose a " + numberOfPlayer + " player game! ");

        // Initialize the dealer
        while (!strResult) {
            input = getInput("Player 0 will be the dealer (Bank), please enter the name for dealer:");
            strResult = (input != null);
        }
        strResult = false;
        this.dealer = new triantaEnaPlayer(input, 300);

        // Initialize each player
        this.players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayer - 1; i++) {
            while (!strResult) {
                input = getInput("Please enter the name for player " + (i + 1) + " : ");
                strResult = (input != null);
            }
            strResult = false;
            triantaEnaPlayer player = new triantaEnaPlayer(input, 100);
            this.players.add(player);
        }
        this.printStream.println("The balance for each player is $100, and the balance for the dealer "
                + this.dealer.getName() + " is $300. \n Now, let's begin.");
    }

    /**
     * player's actions within a single round
     */
    private void playerRound() {
        for (triantaEnaPlayer player : players) {
            this.printStream.println("============ Player " + player.getName() + " This is your round! ============");
            Hand hand = player.getHand();

            // Show information of the dealer's visible card and player's hand card and balance
            triantaEnaInstructions.showInforDealerVisible(this.printStream, dealer);
            triantaEnaInstructions.showInforPlayerHand(this.printStream, player);
            triantaEnaInstructions.showInforPlayerBalance(this.printStream, player);

            // If the player chose to stand previously, jump this round
            if (hand.isStand()) {
                this.printStream.println("You chose to fold. ");
                printStream.println("Go to the next player......");
                continue;
            }
            this.printStream.println("Your current bet for this round is: " + hand.getBet());

            if (checker.isNaturalTriantaEna(hand)) {
                triantaEnaInstructions.showInforPlayerHand(this.printStream, player);
                this.printStream.println("Your current hand is in natural Trianta Ena !!!! Congratulations!!!! " +
                        "\nYou will win the amount of your bet from the dealer: 2 * $" + hand.getBet());
                continue;
            }

            // Every time check the over condition
            while (!checker.isBust(hand) && !checker.isTriantaEna(hand)) {
                String action = getUserAction(player);
                // If user enter 1, he wants to hit, continue to hand out card
                userAct(action, hand);
                triantaEnaInstructions.showInforPlayerHand(this.printStream, player);
                // If the user enters 0, he wants to stand, jump this round
                if (action.equals("0")) {
                    break;
                }
            }

            // check if the hand form a TriantaEna (value adds up to 31)
            if (checker.isTriantaEna(hand)) {
                printStream.println("Congratulations!!! yur current hand is a TriantaEna!!!!");
            }

            // If the hand card exceeds the bust value
            if (checker.isBust(hand)) {
                printStream.println("Player " + player.getName() + ", your hand is bust...... " +
                        "\n your current value is " + hand.getTotalValue());
            }
        }
        printStream.println("___________ \nPlayers' rounds end \n____________");
    }

    /**
     * dealer's actions within a single round
     */
    private void dealerRound() {
        printStream.println("---------------------- Dealer's show time ---------------------");
        Hand hand = dealer.getHand();

        triantaEnaInstructions.showInforDealerHand(printStream, dealer);

        // Check if the dealer's hand is in natural TriantaEna
        if (checker.isNaturalTriantaEna(hand)) {
            printStream.println("Dealer's hand cards are in natural TriantaEna!!!! " +
                    "\n Dealer " + dealer.getName() + " will win all the bets from the other players");
        }

        // If the value of dealer's hand does not exceed 27, he continues to hit
        while (checker.dealerCanHit(hand)) {
            Card newCard = decks[0].dealCard() == null ? decks[1].dealCard() : decks[0].dealCard();
            hand.addCard(newCard);
            printStream.println("Dealer Hits.....");
            triantaEnaInstructions.showInforDealerHand(printStream, dealer);
        }

        // check if the hand of dealer forms a TriantaEna
        if (checker.isTriantaEna(hand)) {
            printStream.println("Congratulations!!! your hand forms a Trianta Ena!!!");
        }

        if (checker.isBust(hand)) {
            printStream.println("Dealer's hand busts!!!!!");
        }

        printStream.println("----------------------- \nDeal's round ends \n____________________");
    }

    /**
     * Calculate results after the actions of players and dealer
     */
    private void calcuRes() {
        ArrayList<triantaEnaPlayer> playerToLeave = new ArrayList<>();
        for (triantaEnaPlayer player : players) {
            // If the player chose to fold, display the balance directly
            if (player.getHand().isStand()) {
                printStream.println("At round " + this.round + ", player " + player.getName() + " chose to fold. " +
                        "\nThe current balance for player " + player.getName() + " is: $" + player.getMoney());
            } else {
                // if the player bet this time, calculate the new balance of this round
                int newBalance = checker.checkWinner(player, dealer);
                triantaEnaInstructions.showInforWinning(printStream, player, newBalance);
            }
            if (player.getMoney() <= 0) {
                playerToLeave.add(player);
                triantaEnaInstructions.showInforLeave(this.printStream, player);
            }
            if (cashOut(player)) {
                triantaEnaInstructions.showInforCashOut(this.printStream, player);
                playerToLeave.add(player);
            }
        }
        // print dealer information
        triantaEnaInstructions.showInforDealer(this.printStream, dealer);

        // remove player
        for (triantaEnaPlayer player : playerToLeave) {
            printStream.println(player.getName());
            this.players.remove(player);
        }
    }

    /**
     * The first bet in one round, players can choose to bet or stand
     *
     * @param player player to make the bet
     */
    private void firstBet(triantaEnaPlayer player) {
        String input = "";
        boolean result = false;
        int currentBalance = player.getMoney();
        this.printStream.println("Player " + player.getName() + " your current balance is $" + player.getMoney());

        // Ask user to input
        while (!result) {
            input = getInput("Please enter an Integer between 1 and " + player.getMoney() + " to bet." +
                    "\n Type 0 to fold.");
            if ((input.matches("[0-9]+")) && Integer.parseInt(input) < player.getMoney()) {
                result = true;
            } else {
                printStream.println("Invalid input. Please enter valid number between 0 and " + player.getMoney() + ":");
            }
        }

        // Update player parameters
        if (input == String.valueOf(0)) {
            player.getHand().setStand(true);
        } else {
            player.getHand().setStand(false);
            player.getHand().setBet(Integer.parseInt(input));
            player.setMoney(-Integer.parseInt(input));
        }

        // display player information after bet
        if (Integer.parseInt(input) == 0) {
            this.printStream.println("Player " + player.getName() + " you chose to stand. " +
                    "\n***** Your current balance remains $" + player.getMoney() + " *****");
        } else {
            this.printStream.println("Player " + player.getName() + " you chose to bet. " +
                    "\n***** Your current balance is $" + player.getMoney() + " after this bet. *****");
        }
        printStream.println("______-----------_________-----------_________---------");
    }

    /**
     * Get user action in a round. user can choose to hit (1) or stand (0)
     *
     * @param player player to make choice
     * @return valid user input
     */
    private String getUserAction(triantaEnaPlayer player) {
        String input = "";
        boolean strResult = false;
        while (!strResult) {
            input = getInput("Please select your next action: \n 1 for hit, 0 for stand");
            strResult = (input.equals("0") || input.equals("1"));
            if (!strResult) {
                printStream.println("Invalid input. Please enter 0 for stand, 1 for hit!");
            }
        }
        if (input.equals("0")) {
            printStream.println("You chose to stand");
        } else if (input.equals("1")) {
            printStream.println("You chose to hit");
        }
        return input;
    }

    /**
     * User actions in a round, they can choose to hit or stand
     *
     * @param action 0 for stand, 1 for hit
     * @param hand   player's hand card
     */
    private void userAct(String action, Hand hand) {
        if (action.equals("0")) {
            // actions for stand
            printStream.println("No action needed, your balance dose not change.");
        } else if (action.equals("1")) {
            // actions for hit, obtain a new card
            Card newCard = decks[0].dealCard() == null ? decks[1].dealCard() : decks[0].dealCard();
            hand.addCard(newCard);
        }
    }

    /**
     * Deal new card to players if the first deck uses up, use the second deck
     */
    private void dealCard() {
        for (int i = 0; i < 2; i++) {
            for (triantaEnaPlayer player : this.players) {
                // If the player's hand is in stand, jump this round, otherwise deal new card
                if (player.getHand().isStand()) continue;
                Card newCard = decks[0].dealCard() == null ? decks[1].dealCard() : decks[0].dealCard();
                player.getHand().addCard(newCard);
            }
            Card newCard = decks[0].dealCard() == null ? decks[1].dealCard() : decks[0].dealCard();
            dealer.getHand().addCard(newCard);
        }
    }

    /**
     * Ask player if they want to cash out
     *
     * @param player player to be asked
     * @return true if they input 0
     */
    private boolean cashOut(triantaEnaPlayer player) {
        boolean out = false;
        String input = "";
        boolean strRes = false;
        while (!strRes) {
            input = getInput("Player " + player.getName() + ", do you want to cash out? " +
                    "\n 0 for no, 1 for yes");
            strRes = (input.equals("0") || input.equals("1"));
        }
        if (input.equals("1")) out = true;
        return out;
    }

    /**
     * Get user input and give them a prompt
     *
     * @param prompt Prompt to be displayed to user
     * @return user input if user input the right format
     * @throws Exception if user input wrong
     */
    private String getInput(String prompt) {
        printStream.println(prompt);
        try {
            String input = scanner.nextLine();
            return input;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * After Each round, card is washed
     */
    private void washCard() {
        for (triantaEnaPlayer player : this.players) {
            player.clearHand();
        }
        dealer.clearHand();
        this.decks = new Deck[2];
        decks[0] = new Deck();
        decks[1] = new Deck();
    }

    /**
     * get current round
     *
     * @return current round number
     */
    public static int getRound() {
        return round;
    }

    /**
     * if the balance of dealer is smaller than any of the others, ask if the player with the highest balance
     * would be the new dealer
     */
    private void rotateDealer() {
        int dealerBalance = dealer.getMoney();
        // a priorityQueue to sort the players according to their balance
        if (this.players.isEmpty()) {
            return;
        }
        PriorityQueue<triantaEnaPlayer> pq = new PriorityQueue<>((a, b) -> b.getMoney() - a.getMoney());
        for (triantaEnaPlayer player : players) {
            pq.offer(player);
        }
        triantaEnaPlayer maxBalancePlayer = pq.poll();

        if (dealerBalance <= 0) {
            // If the money of the dealer is less than 0, find if any other can be the new dealer
            if (players.size() < 1) {
                // If there are no available users, end game
                printStream.println("No other user can the new dealer, game over. \n Thanks for playing!!");
                players.clear();
            } else {
                // If the player with the highest balance refuses to be the new dealer, ask the next player with second
                // highest balance until the last player. If all the other players refuse to be the dealer, the last one
                // has to be the new dealer
                while (!askNewDealer(maxBalancePlayer)) {
                    if (pq.isEmpty()) {
                        printStream.println("No other players, player " + maxBalancePlayer.getName() + ", " +
                                "you have to be the new dealer");
                        changeDealer(this.dealer, maxBalancePlayer);
                        // Add the new player to be the new dealer and remove the previous dealer
                        players.remove(this.dealer);
                    } else {
                        maxBalancePlayer = pq.poll();
                    }
                }
            }
        }
        // If previous dealer's money is less than the player with maximum money, exchange them
        if (dealerBalance < maxBalancePlayer.getMoney()) {
            while (!askNewDealer(maxBalancePlayer)) {
                if (pq.isEmpty()) {
                    printStream.println("No other players, player " + maxBalancePlayer.getName() + ", " +
                            "you have to be the new dealer");
                    changeDealer(this.dealer, maxBalancePlayer);
                    // Add the new player to be the new dealer and remove the previous dealer
                    players.remove(this.dealer);
                } else {
                    maxBalancePlayer = pq.poll();
                }
            }
            changeDealer(this.dealer, maxBalancePlayer);
        }
    }

    private boolean askNewDealer(triantaEnaPlayer player) {
        String input = "";
        boolean strRes = false;
        while (strRes) {
            input = getInput("Player " + player.getName() + ", you are currently the one with the highest balance. " +
                    "\n would you like be the new dealer?\n 0 for no, 1 for yes: ");
            strRes = (input.equals("0") || input.equals("1"));
            if (strRes == false) {
                printStream.println("Please input valid option, 0 for no, 1 for yes:");
            }
        }
        if (input.equals("0")) return false;
        else return true;
    }

    private void changeDealer(triantaEnaPlayer preDealer, triantaEnaPlayer newDealer) {
        this.dealer = newDealer;
        this.players.remove(newDealer);
        this.players.add(preDealer);
    }
}
