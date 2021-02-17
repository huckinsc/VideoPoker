package com.java6casino.videopoker;

import java.util.Objects;

public class Card {
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
        return "{The " + rank + " of " + suit + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getRank() == card.getRank() && getSuit() == card.getSuit();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getSuit());
    }
}