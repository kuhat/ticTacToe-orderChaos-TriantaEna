package com.TriantaEna.tictactoe;

import com.TriantaEna.utils.GameState;
import com.TriantaEna.utils.Player;
import com.TriantaEna.utils.boardGame.boardPlayer;
import com.TriantaEna.utils.boardGame.Board;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToeRunner implements GameState {

    private PrintStream printStream;

    private Scanner scanner;

    private Board board = null;

    private boardPlayer[] boardPlayers = null;

    private final String chessO = "O";

    private final String chessX = "X";

    private int round = 0;

    public TicTacToeRunner(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    @Override
    public boolean isOver() {
        return hasWon(this.boardPlayers[0], this.board) || hasWon(this.boardPlayers[1], board) || !this.board.existEmptyPos();
    }

    /**
     * Run the Tic-tac-toe
     * @param Instruction Initial instruction of the game
     * @param boardPlayers Players playing in this game
     * @param chesses Chesses needed in this game
     */
    @Override
    public void run(String[] Instruction, Player[] boardPlayers, String[] chesses) {
        int win = 3;
        while (true) {
            while (!isOver()) {
                printStream.println(Instruction[0]);
                playerMove(Instruction[0], (boardPlayer) boardPlayers[0], chesses[0]);
                board.printBoard();
                if (hasWon((boardPlayer) boardPlayers[0], this.board)) {
                    win = 0;
                    break;
                } else if (!this.board.existEmptyPos()) {
                    win = 2;
                    break;
                }
                printStream.println(Instruction[1]);
                playerMove(Instruction[1], (boardPlayer) boardPlayers[1], chesses[1]);
                board.printBoard();
                if (hasWon((boardPlayer) boardPlayers[1], this.board)) {
                    win = 1;
                    break;
                }
            }
            if (win == 0) {
                boardPlayers[0].increaseWinningTimes();
                printStream.println("Player " + ((boardPlayer) boardPlayers[0]).getChess() + ": " + boardPlayers[0].getName() + " won!");
            } else if (win == 1) {
                boardPlayers[1].increaseWinningTimes();
                printStream.println("Player " + ((boardPlayer) boardPlayers[1]).getChess() + ": " + boardPlayers[1].getName() + " won!");
            } else if (win == 2) {
                printStream.println("No winner, game over!");
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
            }
            else {
                start();
                break;
            }
        }
    }

    /**
     * Start the ticTacToe game
     */
    @Override
    public void start() {
        if (round == 0) init();
        round++;

        // Choose game board size
        String input = "";
        boolean strResult = false;
        while (!strResult) {
            input = getInput("Please enter board size (Integer > 1):");
            strResult = (input.matches("[0-9]+")) && (Integer.parseInt(input) > 1);
        }
        strResult = false;
        this.board = new Board(Integer.parseInt(input));
        board.printBoard();

        // Choose which player play first, 0 for O, 1 for X
        while (!strResult) {
            input = getInput("O first or X first? Input O for 0, 1 for X:");
            strResult = (input.length() == 1) && (input.equals("0") || input.equals("1"));
        }

        // run the game
        final String[] INSTRUCTION_O = new String[]{"Player O: " + boardPlayers[0].getName() + " ,Enter your move '<row>,<col>'. For example: '2,0': ",
                "Player X: " + boardPlayers[1].getName() + " ,Enter your move '<row>,<col>'. For example: '1,0': "};
        final String[] INSTRUCTION_X = new String[]{"Player X: " + boardPlayers[1].getName() + " Enter your move '<row>,<col>'. For example: '2,0': ",
                "Player O: " + boardPlayers[0].getName() + " ,Enter your move '<row>,<col>'. For example: '1,0': "};
        if (input.equals("0")) {
            run(INSTRUCTION_O, boardPlayers, new String[]{chessO, chessX});
        } else {
            run(INSTRUCTION_X, new boardPlayer[]{boardPlayers[1], boardPlayers[0]}, new String[]{chessX, chessO});
        }
    }

    /**
     * if it is the first round, initialize the variables
     */
    @Override
    public void init() {

        // Initialize chess needed in this game
        String input = "";
        boolean strResult = false;
        ArrayList<String> Oset = new ArrayList<>(), XSet = new ArrayList<>();
        Oset.add(chessO);
        XSet.add(chessX);

        // ask player to enter names for O and X
        while (!strResult) {
            input = getInput("Please enter the name for O");
            strResult = (input != null && input.length() > 0);
        }
        boardPlayer boardPlayerO = new TicTacToeBoardPlayer(Oset, input, 0);
        strResult = false;
        while (!strResult) {
            input = getInput("Please enter the name for X");
            strResult = (input != null && input.length() > 0);
        }
        boardPlayer boardPlayerX = new TicTacToeBoardPlayer(XSet, input, 0);

        // Initialize players in this game
        this.boardPlayers = new boardPlayer[]{boardPlayerO, boardPlayerX};
    }

    private void playerMove(String instruction, boardPlayer boardPlayer, String chess) {
        while (true) {
            String input = "";
            int[] pos = null;
            while (pos == null) {
                input = getInput("");
                pos = parseUserInput(input);
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

    private String getInput(String prompt) {
        printStream.println(prompt);
        try {
            String input = scanner.nextLine();
            return input;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private boolean hasWon(boardPlayer boardPlayer, Board board) {
        ArrayList<String> chesses = boardPlayer.getChess();
        String chess = chesses.get(0);
        return board.validateRow(chess, board.getSize()) || board.validateCol(chess, board.getSize())
                || board.validateDia(chess, board.getSize());
    }
}
