package com.TriantaEna.utils.cardGame;

import com.TriantaEna.utils.Player;

import java.util.ArrayList;

public abstract class cardPlayer implements Player {

    private String name;

    private int money;

    private int bet;

    private ArrayList<Card> current;

    public cardPlayer(String name) {
        this.name = name;
        this.money = 0;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getWinningTimes() {
        return 0;
    }

    @Override
    public void increaseWinningTimes() {

    }
}