package com.TriantaEna.utils.cardGame;

public interface playerAction {

    /**
     * player make deal on the current hand card
     *
     * @param deck instance of deck
     * @param hand instance of player's hand card
     */
    void hit(Deck deck, Hand hand);

    /**
     * The Player ends and maintains the value of the current hand
     */
    void stand();


    /**
     * player pay the dealer money
     * @param money amount of money player need to pay
     */
    void pay(int money);

}
