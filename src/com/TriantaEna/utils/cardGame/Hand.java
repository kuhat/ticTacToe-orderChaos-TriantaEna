package com.TriantaEna.utils.cardGame;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards;

    private boolean stand;

    private int bet;

    public Hand() {
        cards = new ArrayList<Card>();
        stand = false;
        bet = 0;
    }

    /**
     * Add a new card to the hand card
     * @param card New card to be added
     */
    public void addCard(Card card) {
        cards.add(card);
    }


    /**
     * Get the cards of the hand
     * @return cards of the hand
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * get the number of cards currently the hand have
     * @return the size of cards
     */
    public int getCardNum() {
        return cards.size();
    }

    public void removeCard() {

    }

    /**
     * Set current bet of the hand cards
     * @param bet
     */
    public void setBet(int bet) {
        this.bet = bet;
    }

    /**
     * get current bet for this hand
     * @return bet
     */
    public int getBet() {
        return bet;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    public boolean isStand() {
        return stand;
    }

    /**
     * get total value of current hand card
     * @return
     */
    public int getTotalValue() {
        int sum = 0;
        for (int i = 0; i < cards.size(); i++) {
            sum += cards.get(i).getValue();
        }
        return sum;
    }

    /**
     * display the information of current hand card
     * @return the card information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append("(" + cards.get(i).toString() + ")");
        }
        return sb.toString();
    }

}
