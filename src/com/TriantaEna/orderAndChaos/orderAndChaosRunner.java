package com.TriantaEna.orderAndChaos;

import com.TriantaEna.utils.GameState;
import com.TriantaEna.utils.Player;
import com.TriantaEna.utils.boardGame.boardPlayer;
import com.TriantaEna.utils.boardGame.Board;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class orderAndChaosRunner implements GameState {

    private PrintStream printStream;

    private Scanner scanner;

    private Board board = null;

    private boardPlayer[] boardPlayers = null;

    private final String chessOo = "Oo";

    private final String chessOx = "Ox";

    private final String chessCx = "Cx";

    private final String chessCo = "Co";

    private int round = 0;

    public orderAndChaosRunner(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    @Override
    public boolean isOver() {
        return hasWon(boardPlayers[0], this.board) || !this.board.existEmptyPos();
    }

    @Override
    public void run(String[] Instruction, Player[] boardPlayers, String[] chesses) {
        // Win: 0 for player orders, 1 or 2 for player chaos
        int win = 2;
        while (true) {
            while (!isOver()) {
                // Get user 0 chess choice
                String input = "";
                boolean strResult = false;
                while (!strResult) {
                    input = getInput(Instruction[0]);
                    strResult = (input.equals("o") || input.equals("x"));
                }
                strResult = false;
                playerMove(Instruction[1], (boardPlayer) boardPlayers[0], input);
                board.printBoard();
                if (hasWon((boardPlayer) boardPlayers[0], board)) {
                    win = 0;  // Orders win when there exists multiple of 5 same cell
                    break;
                } else if (!this.board.existEmptyPos()) {
                    win = 1;  // if there is no empty cell, Chaos win
                    break;
                }
                while (!strResult) {
                    input = getInput(Instruction[2]);
                    strResult = (input.equals("o") || input.equals("x"));
                }
                playerMove(Instruction[3], (boardPlayer) boardPlayers[1], input);
                board.printBoard();
                if (hasWon((boardPlayer) boardPlayers[0], board)) {
                    win = 0;
                    break;
                } else if (!this.board.existEmptyPos()) {
                    win = 1;
                    break;
                }
            }
            if (win == 0) {
                boardPlayers[0].increaseWinningTimes();
                printStream.println("Player " + boardPlayers[0].getName() + " won!");
            } else {
                boardPlayers[1].increaseWinningTimes();
                printStream.println("Player" + boardPlayers[1].getName() + " won!");
            }
            String input = "";
            boolean strResult = false;
            while (!strResult) {
                input = getInput("If you would like to play another round of game, type 'y', otherwise type 'n'.");
                strResult = (input.equals("y") || input.equals("n"));
            }
            if (input.equals("n")) {
                printStream.println("Game over. Thanks for playing! \n " +
                        "You played " + round + " round. \n" +
                        "Player " + boardPlayers[0].getName() + " won " + boardPlayers[0].getWinningTimes() + " times, \n " +
                        "Player " + boardPlayers[1].getName() + " won " + boardPlayers[1].getWinningTimes() + " times.");
                break;
            } else {
                start();
                break;
            }
        }
    }

    @Override
    public void start() {
        if (round == 0) init();
        round++;

        // Board size is limited to 6 X 6
        this.board = new Board(6);
        printStream.println("This is your game board: ");
        board.printBoard();

        // Choose while player play first, 0 for order, 1 for chaos
        String input = "";
        boolean strResult = false;
        while (!strResult) {
            input = getInput("Order first or Chaos first? Input 0 for order, 1 for Chaos:");
            strResult = (input.length() == 1) && (input.equals("0") || input.equals("1"));
        }

        // Run the game
        final String[] INSTRUCTION_ORDER = new String[]{
                "Player order: " + boardPlayers[0].getName() + " ,Enter your choice, 'o' or 'x': ",
                "Player order: " + boardPlayers[0].getName() + ",Enter your move '<row>,<col>'. For example: '1,0': ",
                "Player chaos: " + boardPlayers[1].getName() + " ,Enter your choice, 'o' or 'x': ",
                "Player chaos: " + boardPlayers[1].getName() + " ,Enter your move '<row>,<col>'. For example: '1,0': "
        };
        final String[] INSTRUCTION_CHAOS = new String[]{
                "Player chaos: " + boardPlayers[1].getName() + " ,Enter your choice, 'o' or 'x': ",
                "Player chaos: " + boardPlayers[1].getName() + " ,Enter your move '<row>,<col>'. For example: '1,0': ",
                "Player order: " + boardPlayers[0].getName() + " ,Enter your choice, 'o' or 'x': ",
                "Player order: " + boardPlayers[0].getName() + ",Enter your move '<row>,<col>'. For example: '1,0': "
        };
        if (input.equals("0")) {
            run(INSTRUCTION_ORDER, boardPlayers, new String[]{""});
        } else {
            run(INSTRUCTION_CHAOS, new boardPlayer[]{boardPlayers[1], boardPlayers[0]}, new String[]{""});
        }
    }

    @Override
    public void init() {
        // Initialize Chess
        String input = "";
        boolean strResult = false;
        ArrayList<String> oSet = new ArrayList<>();
        ArrayList<String> cSet = new ArrayList<>();
        oSet.add(chessOx);
        oSet.add(chessOo);
        cSet.add(chessCx);
        cSet.add(chessCo);

        // ask player to enter names for O and X
        while (!strResult) {
            input = getInput("Please enter the name for player order: ");
            strResult = (input != null && input.length() > 0);
        }
        boardPlayer orderBoardPlayer = new orderAndChaosBoardPlayer(oSet, input, 0);
        strResult = false;
        while (!strResult) {
            input = getInput("Please input the name for player Chaos: ");
            strResult = (input != null && input.length() > 0);
        }
        boardPlayer chaosBoardPlayer = new orderAndChaosBoardPlayer(cSet, input, 0);

        // Initialize players in this game
        this.boardPlayers = new boardPlayer[]{orderBoardPlayer, chaosBoardPlayer};
    }

    private String getInput(String prompt) {
        printStream.println(prompt);
        try {
            String input = scanner.nextLine();
            return input;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private void playerMove(String instruction, boardPlayer boardPlayer, String chess) {
        if (instruction.charAt(7) == 'o') chess = "O" + chess;
        else chess = "C" + chess;
        while (true) {
            String input = "";
            int[] pos = null;
            while (pos == null) {
                input = getInput(instruction);
                pos = parseUserInput(input);
//                printStream.println("input: " + Arrays.toString(pos));
                if (pos == null) printStream.println(instruction);
            }
            try {
                if (boardPlayer.updateBoard(pos[0], pos[1], this.board, chess)) {
                    return;
                }
            } catch (IllegalArgumentException e) {
                printStream.println(e.getMessage() + " (0~" + (board.getSize() - 1) + ")");
            }
        }
    }

    private int[] parseUserInput(String input) {
        String[] pos = input.split(",");
        if (pos.length != 2) {
            return null;
        }
        int row, col;
        try {
            row = Integer.parseInt(pos[0]);
            col = Integer.parseInt(pos[1]);
        } catch (IllegalArgumentException e) {
            return null;
        }
        return new int[]{row, col};
    }

    private boolean hasWon(boardPlayer boardPlayer, Board board) {
        ArrayList<String> chesses = boardPlayer.getChess();
        String chess1 = chesses.get(0).charAt(1) + "";
        String chess2 = chesses.get(1).charAt(1) + "";

        return board.validateRow(chess1, 5) || board.validateCol(chess1, 5)
                || board.validateDia(chess1, 5) || board.validateRow(chess2, 5)
                || board.validateCol(chess2, 5) || board.validateDia(chess2, 5);
    }
}