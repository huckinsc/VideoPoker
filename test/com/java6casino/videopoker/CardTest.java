package com.java6casino.videopoker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    Card c1,c2,c3,c4;

    @Before
    public void setUp() {
        c1 = new Card(Rank.ACE,Suit.SPADES);
        c2 = new Card(Rank.FOUR,Suit.CLUBS);
        c3 = new Card(Rank.KING,Suit.DIAMONDS);
        c4 = new Card(Rank.TEN,Suit.HEARTS);
    }

    @Test
    public void generatePrintString() {
        assertEquals("[ A♠]",c1.generatePrintString());
        assertEquals("[ 4♣]",c2.generatePrintString());
        assertEquals("[ K♦]",c3.generatePrintString());
        assertEquals("[10♥]",c4.generatePrintString());

    }
}