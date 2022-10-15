package com.TriantaEna.utils.boardGame;

/**
 *
 * This is one cell of the game grid
 *
 * @author wenhao
 */

public class Cell {

    private boolean isEmpty;

    private String content;

    public Cell() {
        this.isEmpty = true;
        this.content = " ";
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null) {

            System.out.println("Invalid content");
        } else {
            this.content = content;
        }
    }
}
