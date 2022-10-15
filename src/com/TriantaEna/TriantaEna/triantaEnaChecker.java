package com.TriantaEna.TriantaEna;

import com.TriantaEna.utils.cardGame.Card;
import com.TriantaEna.utils.cardGame.Hand;

public class triantaEnaChecker {

    private int max_value;

    private int dealer_val;

    private final int winning_value = 31;


    public triantaEnaChecker(int max_value, int dealer_val) {
        this.max_value = max_value;
        this.dealer_val = dealer_val;
    }

    /**
     * Check if the current hand's value exceeds the max value
     * @param hand hand of cards to be checked
     * @return true if the total value of cards exceeds the max value
     */
    public boolean isBust(Hand hand) {
        return hand.getTotalValue() > max_value;
    }

    /**
     * Check if the current hand's value equals winning_value
     * @param hand current checking hand
     * @return true if the total value of the hand is equal to 31
     */
    public boolean isTriantaEna(Hand hand) {
        return hand.getTotalValue() == winning_value;
    }

    /**
     * check if the original hand card is in natural TriantaEna
     * @param hand hand to be checked
     * @return true if the length of hand is 3 and the value adds up to 31
     */
    public boolean isNaturalTriantaEna(Hand hand) {
        if (hand.getCardNum() != 3)
            return false;
        int ace = 0;
        int face = 0;
        for (Card card : hand.getCards()) {
            if (card.getValue() == 1) {
                ace++;
            } else if (card.getValue() == 10) {
                face++;
            }
        }
        return ace == 1 && face == 2;
    }

    /**
     * check if the dealer is winning
     * @param hand hand of the dealer
     * @return true if the sum of the value from the hand of dealer exceed dealer value
     */
    public boolean dealerCanHit(Hand hand) {
        return hand.getTotalValue() < dealer_val;
    }

    /**
     * After each round, check winning conditions.  the winner of a round is any
     * Player who has a hand value higher than that of the Dealer without having gone bust. In
     * the case of a tie, the Dealer wins. A natural 31 of the Banker results in the
     * Banker winning the bets from all players
     * @param player player to be compared
     * @param dealer dealer to be compared
     * @return newBalance < 0 if player loses, newBalance > 0 if player wins
     */
    public int checkWinner(triantaEnaPlayer player, triantaEnaPlayer dealer) {
        Hand dealerHand = dealer.getHand();
        Hand playerHand = player.getHand();
        int newBalance = 0;
        int value = playerHand.getTotalValue();
        int dealerValue = dealer.getHandTotalValue();
        int bet = playerHand.getBet();

        // If the dealer did not go bust
        if (!isBust(dealerHand)) {
            if (isBust(playerHand)) {
                // if player hand bust, player hand loses, and banker get the bet
                newBalance -= bet;
                dealer.setMoney(bet);
            } else {
                // if player hand did not bust
                if (value < dealerValue) {
                    // if player value is smaller than dealer hand value, player hand loses
                    newBalance -= bet;
                    dealer.setMoney(bet);
                } else if (value == dealerValue) {
                    if (isNaturalTriantaEna(dealerHand) && isNaturalTriantaEna(playerHand)) {
                        // both banker hand and player hand is natural Trianta Ena, tie. if tie, player loses
                        dealer.setMoney(bet);
                        newBalance -= bet;
                    } else if (isNaturalTriantaEna(dealerHand) && !isTriantaEna(playerHand)) {
                        // banker hand == natural Trianta Ena && player hand == Trianta Ena, player hand loses
                        dealer.setMoney(bet);
                        newBalance -= bet;
                    } else if (isTriantaEna(dealerHand) && isNaturalTriantaEna(playerHand)) {
                        // banker hand == Trianta Ena && player hand == natural Trianta Ena, player hand wins
                        // Player win their bet * 2 from the Dealer
                        newBalance += bet;
                        player.setMoney(bet * 2);
                        dealer.setMoney(-bet);
                    } else {
                        // If both Trianta Ena or neither Trianta Ena, nor natural Trianta Ena, tie. Player loss
                        dealer.setMoney(bet);
                        newBalance -= bet;
                    }
                } else {
                    // if player value bigger than dealer hand value, player wins
                    newBalance += bet;
                    player.setMoney(bet * 2);
                    dealer.setMoney(-bet);
                }
            }

        } else {
            // else if dealer busted
            if (!isBust(playerHand)) {
                // if Player did not bust, player wins
                dealer.setMoney(-bet);
                player.setMoney(2 * bet);
                newBalance += playerHand.getBet();
            } else {
                // if the player busted, tie. dealer wins
                dealer.setMoney(bet);
                newBalance -= bet;
            }
        }
        return newBalance;
    }


}
