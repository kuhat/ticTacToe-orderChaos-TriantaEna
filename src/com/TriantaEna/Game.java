package com.TriantaEna;

import com.TriantaEna.TriantaEna.triantaEnaRunner;
import com.TriantaEna.orderAndChaos.orderAndChaosRunner;
import com.TriantaEna.tictactoe.TicTacToeRunner;

import java.util.Scanner;

public class Game {

    public static void startGame() {
        System.out.println("Welcome to TicTacToe && Order and Chaos && TriantaEna! \n" +
                "Please enter the game number you want to play " +
                "\n 1 for TicTacToe, \n 2 for Order and Chaos, " +
                "\n 3 for Trianta Ena:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")) {
            System.out.println("please enter valid number!");
            input = scanner.nextLine();
        }
        if (input.equals("1")){
            System.out.println("You chose TicTacToe!");
            TicTacToeRunner tictactoerunner = new TicTacToeRunner(scanner, System.out);
            tictactoerunner.start();
        } else if (input.equals("2")) {
            System.out.println("You chose Order and Chaos!");
            orderAndChaosRunner orderAndChaosRunner = new orderAndChaosRunner(scanner, System.out);
            orderAndChaosRunner.start();
        } else if (input.equals("3")) {
            System.out.println("You chose to play TriantaEna!");
            triantaEnaRunner triantaEnaRunner = new triantaEnaRunner(scanner, System.out);
            triantaEnaRunner.start();
        }
    }
}
