package com.TriantaEna.tictactoe;

import com.TriantaEna.utils.boardGame.boardPlayer;
import com.TriantaEna.utils.boardGame.Board;

import java.util.ArrayList;

public class TicTacToeBoardPlayer extends boardPlayer {

    private ArrayList<String> chess;
    private String name;

    public TicTacToeBoardPlayer(ArrayList<String> chess, String name, int winningTimes){
        super(chess, name, winningTimes);
        this.chess = chess;
        this.name = name;
    }

    /**
     * Player updates the corresponding cell
     * @param row cell row position
     * @param col cell column position
     * @param board the board to be updated
     * @param choice the String to be added into the position
     * @return true if it succeeded in adding the value
     * @throws IllegalArgumentException if the user inputs an illegal number beyond the board scope
     * @throws IllegalArgumentException if the user inputs a wrong chess String
     */

    public boolean updateBoard(int row, int col, Board board, String choice) {
        System.out.println(choice);
        if (row >= board.getSize() || col >= board.getSize() || row < 0 || col < 0) {
            System.out.println("Size: " + board.getSize() + "row: " + row);
            throw new IllegalArgumentException("Please enter legal number!");
        } else if (!chess.contains(choice)) {
            throw new IllegalArgumentException("Please enter legal chess choice!");
        } else {
            try {
                board.updateCell(row, col, choice);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public boolean containsChess(String choice) {
        return chess.contains(choice);
    }

    public ArrayList<String> getChess() {
        return this.chess;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
