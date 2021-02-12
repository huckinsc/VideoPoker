package com.java6casino.videopoker;

enum Suit {
    CLUBS('\u2663'), DIAMONDS('\u2666'), HEARTS('\u2665'), SPADES('\u2660');

    private final char suitCharacter;

    Suit(char suitCharacter) {
        this.suitCharacter = suitCharacter;
    }

    public char getSuitCharacter() {
        return suitCharacter;
    }
}