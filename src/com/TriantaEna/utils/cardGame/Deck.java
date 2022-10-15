package com.TriantaEna.utils.cardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    public final static String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public final static String[] suits = {"Diamonds", "Clubs", "Hearts", "spades"};

    public static ArrayList<Card> oneDeck;

    private int cardNum = 52;

    /**
     * Initialize a deck of cards
     */
    public Deck() {
        oneDeck = new ArrayList<>();
        for (String suit : suits) {
            for (String value : values) {
                oneDeck.add(new Card(value, suit));
            }
        }
        for (Card c : oneDeck) {
            c.setVisible(true);
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(oneDeck);
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int num) {
        cardNum += num;
    }

    public Card dealCard() {
        if (getCardNum() == 0) {
            return null;
        }
        setCardNum(-1);
        return oneDeck.remove(0);
    }

}
