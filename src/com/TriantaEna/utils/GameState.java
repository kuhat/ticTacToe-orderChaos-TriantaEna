package com.TriantaEna.utils;

import com.TriantaEna.utils.boardGame.boardPlayer;

public interface GameState {

    /**
     * Judge if the game reaches over condition
     * @return True if the condition is met
     */
    boolean isOver();

    /**
     * run the game
     * @param startInstructions Initial instruction of the game
     * @param Players Players playing in this game
     * @param chesses Chesses needed in this game
     */
    void run(String[] startInstructions, Player[] Players, String[] chesses);

    /**
     * start the game
     */

    void start();

    void init();


}