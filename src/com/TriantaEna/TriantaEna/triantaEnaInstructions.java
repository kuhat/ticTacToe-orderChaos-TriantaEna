package com.TriantaEna.TriantaEna;

import com.TriantaEna.utils.Player;

import java.io.PrintStream;

public class triantaEnaInstructions {

    /**
     * initial message for running this game
     *
     * @param printStream printStream used to print
     */
    public static void initInstruction(PrintStream printStream) {
        printStream.println("++++++++++++ Welcome to the special edition of Black Jack ++++++++++++++");
        printStream.println("+++++++++++++++++++++ ----- Trianta Ena ----- ++++++++++++++++++++++++++");
        printStream.println(" This is a terminal based Casino game, pay attention to the output of terminal: \n" +
                "You will be handed a standard card with value numbers from 2 to 10 and J, Q, K, A \n" +
                "The Face cards J Q K are of 10 value each and A can be either 1 or 11.\n" +
                "However, if there are more than one A in your hands, only one can be considered as 1.");
        printStream.println("*++++++++++++++++++++++*++  Now Let's begin  +++++++++++++++++++*********");
    }

    public static void showInforPlayerHand(PrintStream printStream, triantaEnaPlayer player) {
        printStream.println("Player " + player.getName() + " your current hand card are: " + player.getHand().toString());
        printStream.println("Your total value is: " + player.getHandTotalValue());
    }

    public static void showInforPlayerBalance(PrintStream printStream, triantaEnaPlayer player) {
        printStream.println("Player " + player.getName() + ", your total balance is: $" + player.getMoney());
    }

    public static void showInforDealerVisible(PrintStream printStream, triantaEnaPlayer dealer) {
        printStream.println("Deal's visible cards are: " + dealer.getHand().toString());
    }

    public static void showInforDealerHand(PrintStream printStream, triantaEnaPlayer dealer) {
        printStream.println("Dealer " + dealer.getName() + ", your current hand cards are: " + dealer.getHand().toString());
        printStream.println("Your total value is: " + dealer.getHandTotalValue());
    }

    public static void showInforWinning(PrintStream printStream, triantaEnaPlayer player, int balance) {
        if (balance < 0) {
            printStream.println("Round " + triantaEnaRunner.getRound() + ", player " + player.getName() + " loses $" + balance + ".");
        } else if (balance > 0) {
            printStream.println("Round" + triantaEnaRunner.getRound() + ", Player " + player.getName() + " wins $" + balance + ".");
        } else {
            printStream.println("Round" + triantaEnaRunner.getRound() + ", Player " + player.getName() + " ties with the dealer.");
        }
        showInforPlayerBalance(printStream, player);
    }

    public static void showInforLeave(PrintStream printStream, triantaEnaPlayer player) {
        printStream.println("Sorry, this round, Player " + player.getName() + ", your money runs out. " +
                "\n you are out with cash $" + player.getMoney());
    }

    public static void showInforCashOut(PrintStream printStream, triantaEnaPlayer player) {
        printStream.println("Player leaves this game with cash: $" + player.getMoney());
    }

    public static void showInforDealer(PrintStream printStream, triantaEnaPlayer player) {
        printStream.println("At round " + triantaEnaRunner.getRound() + " dealer hava got balance for $" + player.getMoney());
    }
}
