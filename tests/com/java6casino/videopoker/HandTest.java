package com.java6casino.videopoker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HandTest {
    private Hand hand;

    @Before
    public void setUp() {
        hand = new Hand();
    }

    @Test
    public void replaceCard_shouldReturnNewCardAtIndex_whenValidIndex() {
        hand.addCard(new Card(Rank.KING,Suit.SPADES));
        hand.addCard(new Card(Rank.NINE,Suit.SPADES));
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.KING,Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE,Suit.CLUBS));
        hand.replaceCard(2,new Card(Rank.ACE,Suit.HEARTS));
        assertEquals(new Card(Rank.ACE,Suit.HEARTS), hand.getHand().get(2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void replaceCard_shouldThrowException_whenInvalidIndex() {
        hand.addCard(new Card(Rank.KING,Suit.SPADES));
        hand.addCard(new Card(Rank.NINE,Suit.SPADES));
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.KING,Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE,Suit.CLUBS));
        hand.replaceCard(6,new Card(Rank.ACE,Suit.HEARTS));
    }

    @Test
    public void checkSuits_shouldReturnTrue_whenAllSuitsSame() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.TEN,Suit.HEARTS));
        list.add(new Card(Rank.NINE,Suit.HEARTS));
        list.add(new Card(Rank.THREE,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        list.add(new Card(Rank.ACE,Suit.HEARTS));
        assertTrue(hand.checkSuits(list));

    }

    @Test
    public void checkSuits_shouldReturnFalse_whenAllSuitsDiffer() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.TEN,Suit.HEARTS));
        list.add(new Card(Rank.NINE,Suit.DIAMONDS));
        list.add(new Card(Rank.THREE,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        list.add(new Card(Rank.ACE,Suit.HEARTS));
        assertFalse(hand.checkSuits(list));
    }

    @Test
    public void calculateRankCounts_shouldReturnMapWithFiveKeyAndOneCounts_whenAllRanksDiffer() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.TEN,Suit.HEARTS));
        list.add(new Card(Rank.NINE,Suit.DIAMONDS));
        list.add(new Card(Rank.THREE,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        list.add(new Card(Rank.ACE,Suit.HEARTS));
        Map<Rank, Integer> map = hand.calculateRankCounts(list);
        assertEquals(5, map.size());
        for (Map.Entry e : map.entrySet()) {
            assertEquals(1, e.getValue());
        }
    }

    @Test
    public void calculateRankCounts_shouldReturnMapWithTwoKeyAndTwoThreeCounts_whenAllRanksDiffer() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.TEN,Suit.HEARTS));
        list.add(new Card(Rank.TEN,Suit.DIAMONDS));
        list.add(new Card(Rank.TEN,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        Map<Rank, Integer> map = hand.calculateRankCounts(list);
        assertEquals(2, map.size());
        assertEquals((Integer) 3, map.get(Rank.TEN));
        assertEquals((Integer) 2, map.get(Rank.KING));
    }

    @Test
    public void calculateWin_shouldReturnROYAL_FLUSH_whenHighSequenceAndSameSuit() {
        hand.addCard(new Card(Rank.TEN,Suit.SPADES));
        hand.addCard(new Card(Rank.ACE,Suit.SPADES));
        hand.addCard(new Card(Rank.QUEEN,Suit.SPADES));
        hand.addCard(new Card(Rank.JACK,Suit.SPADES));
        hand.addCard(new Card(Rank.KING,Suit.SPADES));
        WinType type = hand.calculateWin();
        assertEquals(WinType.ROYAL_FLUSH,type);
    }

    @Test
    public void calculateWin_shouldReturnSTRAIGHT_FLUSH_whenINSequenceAndSameSuit() {
        hand.addCard(new Card(Rank.TEN,Suit.SPADES));
        hand.addCard(new Card(Rank.EIGHT,Suit.SPADES));
        hand.addCard(new Card(Rank.QUEEN,Suit.SPADES));
        hand.addCard(new Card(Rank.JACK,Suit.SPADES));
        hand.addCard(new Card(Rank.NINE,Suit.SPADES));
        WinType type = hand.calculateWin();
        assertEquals(WinType.STRAIGHT_FLUSH,type);
    }

    @Test
    public void calculateWin_shouldReturnFOUR_OF_A_KIND_whenFourCardsSameRank() {
        hand.addCard(new Card(Rank.KING,Suit.SPADES));
        hand.addCard(new Card(Rank.NINE,Suit.SPADES));
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.KING,Suit.HEARTS));
        hand.addCard(new Card(Rank.KING,Suit.CLUBS));
        WinType type = hand.calculateWin();
        assertEquals(WinType.FOUR_OF_A_KIND,type);
    }

    @Test
    public void calculateWin_shouldReturnFULL_HOUSE_whenTwoRanks() {
        hand.addCard(new Card(Rank.KING,Suit.SPADES));
        hand.addCard(new Card(Rank.NINE,Suit.SPADES));
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.KING,Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE,Suit.CLUBS));
        WinType type = hand.calculateWin();
        assertEquals(WinType.FULL_HOUSE,type);
    }

    @Test
    public void calculateWin_shouldReturnFLUSH_whenAllSameSuit() {
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.NINE,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.EIGHT,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.THREE,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.FOUR,Suit.DIAMONDS));
        WinType type = hand.calculateWin();
        assertEquals(WinType.FLUSH,type);
    }

    @Test
    public void calculateWin_shouldReturnTHREE_OF_A_KIND_whenTHREERanksSame() {
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.NINE,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.EIGHT,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE,Suit.SPADES));
        WinType type = hand.calculateWin();
        assertEquals(WinType.THREE_OF_A_KIND,type);
    }

    @Test
    public void calculateWin_shouldReturnTWO_PAIRS_whenTwoPair() {
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.NINE,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.EIGHT,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.KING,Suit.SPADES));
        WinType type = hand.calculateWin();
        assertEquals(WinType.TWO_PAIRS,type);
    }

    @Test
    public void calculateWin_shouldReturnJACK_OR_BETTER_whenOnePairBetterThanJacks() {
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.EIGHT,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.JACK,Suit.HEARTS));
        hand.addCard(new Card(Rank.FOUR,Suit.SPADES));
        WinType type = hand.calculateWin();
        assertEquals(WinType.JACKS_OR_BETTER,type);
    }

    @Test
    public void calculateWin_shouldReturnNO_WIN_whenOnePairLessThanJacks() {
        hand.addCard(new Card(Rank.FOUR,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.TEN,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.EIGHT,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.KING,Suit.SPADES));
        WinType type = hand.calculateWin();
        assertEquals(WinType.NO_WIN,type);
    }

    @Test
    public void isInSequence_shouldReturnFalse_whenNotInSequence() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.ACE,Suit.HEARTS));
        list.add(new Card(Rank.THREE,Suit.DIAMONDS));
        list.add(new Card(Rank.NINE,Suit.HEARTS));
        list.add(new Card(Rank.QUEEN,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        assertFalse(hand.isInSequence(list));
    }

    @Test
    public void isInSequence_shouldReturnTrue_whenLowSequence() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.ACE,Suit.HEARTS));
        list.add(new Card(Rank.TWO,Suit.DIAMONDS));
        list.add(new Card(Rank.THREE,Suit.HEARTS));
        list.add(new Card(Rank.FOUR,Suit.HEARTS));
        list.add(new Card(Rank.FIVE,Suit.HEARTS));
        assertTrue(hand.isInSequence(list));
    }

    @Test
    public void isInSequence_shouldReturnTrue_whenMidSequence() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.EIGHT,Suit.HEARTS));
        list.add(new Card(Rank.NINE,Suit.DIAMONDS));
        list.add(new Card(Rank.TEN,Suit.HEARTS));
        list.add(new Card(Rank.JACK,Suit.HEARTS));
        list.add(new Card(Rank.QUEEN,Suit.HEARTS));
        assertTrue(hand.isInSequence(list));
    }

    @Test
    public void isInSequence_shouldReturnTrue_whenHighSequence() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.ACE,Suit.HEARTS));
        list.add(new Card(Rank.TEN,Suit.DIAMONDS));
        list.add(new Card(Rank.JACK,Suit.HEARTS));
        list.add(new Card(Rank.QUEEN,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        assertTrue(hand.isInSequence(list));
    }

    @Test
    public void isHighStraight_shouldReturnTrue_whenHighSequence() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.ACE,Suit.HEARTS));
        list.add(new Card(Rank.TEN,Suit.DIAMONDS));
        list.add(new Card(Rank.JACK,Suit.HEARTS));
        list.add(new Card(Rank.QUEEN,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        assertTrue(hand.isHighStraight(list));
    }

    @Test
    public void isHighStraight_shouldReturnFalse_whenNotInSequence() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.ACE,Suit.HEARTS));
        list.add(new Card(Rank.NINE,Suit.DIAMONDS));
        list.add(new Card(Rank.JACK,Suit.HEARTS));
        list.add(new Card(Rank.QUEEN,Suit.HEARTS));
        list.add(new Card(Rank.KING,Suit.HEARTS));
        assertFalse(hand.isHighStraight(list));
    }

    @Test
    public void isHighStraight_shouldReturnFalse_whenNotHighSequence() {
        List<Card> list = new ArrayList<>();
        list.add(new Card(Rank.EIGHT,Suit.HEARTS));
        list.add(new Card(Rank.NINE,Suit.DIAMONDS));
        list.add(new Card(Rank.TEN,Suit.HEARTS));
        list.add(new Card(Rank.JACK,Suit.HEARTS));
        list.add(new Card(Rank.QUEEN,Suit.HEARTS));
        assertFalse(hand.isHighStraight(list));
    }

}