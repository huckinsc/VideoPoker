package com.java6casino.videopoker;

class Card {
    // Fields
    Rank rank;
    Suit suit;

    // ctors
    Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    // Business Methods
    public void print() {
        System.out.println(generatePrintString());
    }

    public String generatePrintString() {
        return "[" + rank.getRankCharacter() + suit.getSuitCharacter() + "]";
    }

    // Getters
    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card{Rank= " + rank + " Suit= " + suit + "}";
    }
}