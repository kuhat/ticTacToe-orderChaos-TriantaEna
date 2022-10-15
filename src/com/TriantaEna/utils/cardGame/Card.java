package com.TriantaEna.utils.cardGame;

public class Card {

    private String suit;

    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
    private String value;

    private boolean visible = true;

    public Card(String value, String suit) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return this.suit;
    }

    public int getValue() {
        if (value.equals("J") || value.equals("Q") || value.equals("K"))
            return 10;
        if (value.equals("A"))
            return 1;
        return Integer.parseInt(this.value);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return this.suit + ", " + this.value;
    }

}
