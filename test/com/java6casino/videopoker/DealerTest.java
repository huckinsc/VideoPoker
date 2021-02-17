package com.java6casino.videopoker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DealerTest {
    Dealer dealer;

    @Before
    public void setUp() {
        dealer = new Dealer();
        dealer.receiveBet(5);
    }

    @Test
    public void payoutWinnings_shouldReturn0_whenNoWin() {
        assertEquals(0, dealer.payoutWinnings(WinType.NO_WIN));
    }

    @Test
    public void payoutWinnings_shouldReturn5_whenackPlus() {
        assertEquals(5, dealer.payoutWinnings(WinType.JACKS_OR_BETTER));
    }

    @Test
    public void payoutWinnings_shouldReturn10_whenTwoPair() {
        assertEquals(10, dealer.payoutWinnings(WinType.TWO_PAIRS));
    }

    @Test
    public void payoutWinnings_shouldReturn15_when3OfAKind() {
        assertEquals(15, dealer.payoutWinnings(WinType.THREE_OF_A_KIND));
    }

    @Test
    public void payoutWinnings_shouldReturn20_whenStraight() {
        assertEquals(20, dealer.payoutWinnings(WinType.STRAIGHT));
    }

    @Test
    public void payoutWinnings_shouldReturn30_whenFlush() {
        assertEquals(30, dealer.payoutWinnings(WinType.FLUSH));
    }

    @Test
    public void payoutWinnings_shouldReturn45_whenFullHouse() {
        assertEquals(45, dealer.payoutWinnings(WinType.FULL_HOUSE));
    }

    @Test
    public void payoutWinnings_shouldReturn45_when4ofAKind() {
        assertEquals(125, dealer.payoutWinnings(WinType.FOUR_OF_A_KIND));
    }

    @Test
    public void payoutWinnings_shouldReturn250_whenStraightFlush() {
        assertEquals(250, dealer.payoutWinnings(WinType.STRAIGHT_FLUSH));
    }

    @Test
    public void payoutWinnings_shouldReturn250_whenRoyalFlush() {
        assertEquals(1250, dealer.payoutWinnings(WinType.ROYAL_FLUSH));
    }

}