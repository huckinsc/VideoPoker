package com.java6casino.videopoker;

public enum WinType {

    ROYAL_FLUSH(250), STRAIGHT_FLUSH(50), FOUR_OF_A_KIND(25), FULL_HOUSE(9), FLUSH(6), STRAIGHT(4),
    THREE_OF_A_KIND(3), TWO_PAIRS(2), JACKS_OR_BETTER(1), NO_WIN(0);

    private int payOut;

    WinType(int payOut){
        this.payOut = payOut;
    }

    public int getPayOut(){
        return payOut;
    }
}