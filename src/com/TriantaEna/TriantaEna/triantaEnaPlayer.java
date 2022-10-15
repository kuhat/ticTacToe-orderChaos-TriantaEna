package com.TriantaEna.TriantaEna;

import com.TriantaEna.utils.cardGame.Card;
import com.TriantaEna.utils.cardGame.Hand;
import com.TriantaEna.utils.cardGame.cardPlayer;

public class triantaEnaPlayer extends cardPlayer {
    private String name;

    private int money;

    private Hand hand;

    public triantaEnaPlayer(String name, int money) {
        super(name);
        this.name = name;
        this.money = money;
        this.hand = new Hand();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money += money;
    }

    /**
     * Get player's current hand cards
     * @return hand of cards
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * player use a new hand of card
     */
    public void clearHand() {
        this.hand = new Hand();
    }

    /**
     * get the sum of the current hand card value
     * @return sum of the card value
     */
    public int getHandTotalValue() {
        int sum = 0;
        for (Card card: hand.getCards()) {
            sum += card.getValue();
        }
        return sum;
    }
}
