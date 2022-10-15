package com.TriantaEna.orderAndChaos;

import com.TriantaEna.utils.boardGame.boardPlayer;
import com.TriantaEna.utils.boardGame.Board;

import java.util.ArrayList;

public class orderAndChaosBoardPlayer extends boardPlayer {

    private ArrayList<String> chess;

    private String name;

    public orderAndChaosBoardPlayer(ArrayList<String> chess, String name, int winningTimes){
        super(chess, name, winningTimes);
        this.chess = chess;
        this.name = name;
    }

    public boolean containsChess(String choice) {
        return chess.contains(choice);
    }

    public boolean updateBoard(int row, int col, Board board, String choice) {
//        System.out.println("input: " + choice);
//        System.out.println("board chess: " + chess);
        if (row > board.getSize() || col > board.getSize() || row < 0 || col < 0) {
            throw new IllegalArgumentException("Please enter legal number!");
        } else if (!chess.contains(choice)) {
            throw new IllegalArgumentException("Please enter legal chess choice!");
        } else {
            try {
                board.updateCell(row, col, choice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getChess() {
        return this.chess;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
