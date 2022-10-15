package com.TriantaEna.utils.boardGame;

import com.TriantaEna.utils.Player;
import com.TriantaEna.utils.boardGame.Board;

import java.util.ArrayList;

public abstract class boardPlayer implements Player {

    private ArrayList<String> chess;

    private String name;

    private int winningTimes = 0;

    public boardPlayer(ArrayList<String> chess, String name, int winningTimes) {
        this.chess = chess;
        this.name = name;
        this.winningTimes = winningTimes;
    }


    public abstract boolean updateBoard(int row, int col, Board board, String choice);

    public abstract boolean containsChess(String choice);

    public abstract ArrayList<String> getChess();

    public abstract String getName();

    public int getWinningTimes() {
        return winningTimes;
    }

    public void increaseWinningTimes() {
        winningTimes++;
    }

}
