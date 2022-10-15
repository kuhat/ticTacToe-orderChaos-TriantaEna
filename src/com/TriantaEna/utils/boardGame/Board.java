package com.TriantaEna.utils.boardGame;

public class Board {

    private int size;

    private Cell[][] board;

    private int currentCellNum;

    /**
     * Initialize the Board
     *
     * @param size The actual size of the game board; cannot be null
     */
    public Board(int size) {
        this.size = size;
        this.board = new Cell[size][size];
        this.currentCellNum = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Print out the current game board
     *
     */

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getContent().length() > 1) {
                    System.out.print(board[i][j].getContent());
                } else {
                    System.out.print(" " + board[i][j].getContent());
                }
                if (j != size - 1) System.out.print(" |");
            }
            System.out.println();
            if (i != size - 1) {
                for (int j = 0; j < size - 1; j++) System.out.print("-+-+");
                System.out.print("-+-");
            }
            System.out.println();
        }
    }

    public void updateCell(int Row, int Col, String content) {
        if (content == null) throw new RuntimeException();
        // if the position is empty, update this position to content String, else throw an exception
        if (this.board[Row][Col].getContent().equals(" ")) {
            this.board[Row][Col].setContent(content);
            this.currentCellNum ++;
            this.board[Row][Col].setEmpty(false);
        }
        else throw new IllegalArgumentException("Position has already bean taken. Please try other positions.");
    }

    /**
     * check if the board is empty or not
     * @return true if there are empty Cells in the board
     */
    public boolean existEmptyPos() {
        if (currentCellNum == size * size) return false;
        return true;
    }

    /**
     * Find if there exists row that the continuous chess equals the num
     * @param chess Sting of content to be found
     * @param num continuous number of content
     * @return true if there exists num of continuous same Cell
     */
    public boolean validateRow(String chess, int num) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - num + 1; j++) {
                for (int k = 0; k < num; k++) {
                    if (board[i][j +k].getContent().length() > 1) {
                        if (!(board[i][j + k].getContent().charAt(1) + "").equals(chess)) break;
                    } else {
                        if (!board[i][j + k].getContent().equals(chess)) break;
                    }
                    if (k == j + num - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Find if there exists Column that the continuous chess equals the num
     * @param chess Sting of content to be found
     * @param num continuous number of content
     * @return true if there exists num of continuous same Cell
     */
    public boolean validateCol(String chess, int num){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - num + 1; j++) {
                for (int k = 0; k < num; k++) {
                    if (board[j + k][i].getContent().length() > 1) {
                        if (!(board[j + k][i].getContent().charAt(1) + "").equals(chess)) break;
                    } else {
                        if (!board[j + k][i].getContent().equals(chess)) break;
                    }
                    if (!board[j + k][i].getContent().equals(chess)) break;
                    if (k == j + num - 1) return true;
                }
            }
        }
        return false;
    }

    /**
     * Find if there exists Dia that the continuous chess equals the num
     * @param chess Sting of content to be found
     * @param num continuous number of content
     * @return true if there exists num of continuous same Cell
     */
    public boolean validateDia(String chess, int num) {
        // Check for Order and Chaos
        if (num == 5 && board.length == 6) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i + j][i + j].getContent().length() > 1) {
                        if (!(board[i + j][i + j].getContent().charAt(1) + "").equals(chess)) break;
                    } else {
                        if (!(board[i + j][i + j].getContent()).equals(chess)) break;
                    }
                    if (j == 4) return true;
                }
            }
            for (int i = 1; i < 6; i++) {
                if (board[i][i - 1].getContent().length() > 1) {
                    if (!(board[i][i - 1].getContent().charAt(1) + "").equals(chess)) break;
                } else {
                    if (!(board[i][i - 1].getContent()).equals(chess)) break;
                }
                if (i == 5) return true;
            }
            for (int i = 1; i < 6; i++) {
                if (board[i - 1][i].getContent().length() > 1) {
                    if (!(board[i - 1][i].getContent().charAt(1)+ "").equals(chess)) break;
                } else {
                    if (!(board[i - 1][i].getContent()).equals(chess)) break;
                }
                if (i == 5) return true;
            }
            return false;
        } else {
            // Check for ticTaToe
            for (int i = 0; i < board.length; i++) {
                if (!board[i][i].getContent().equals(chess)) break;
                if (i == board.length - 1) return true;
            }
            for (int i = 0; i < board.length; i++) {
                if (!board[i][board.length - i - 1].equals(chess)) break;
                if (i == board.length - 1) return true;
            }
            return false;
        }

    }
}
