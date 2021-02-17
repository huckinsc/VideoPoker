package com.java6casino.videopoker;


//import jdk.internal.jrtfs.JrtFileAttributeView;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    // Constants
    private final int HAND_SIZE = 5;

    //FIELDS
    private PlayPhase phase;
    private Deck dealDeck;
    private Hand playerHand;
    private Player p1;
    private int bet;


    //CONSTRUCTORS

    public Dealer() {
        //dealDeck = new Deck();
        //playerHand = new Hand();
        p1 = new Player("Player");
        phase = PlayPhase.BETTING;
    }

    //METHODS
    private void deal() {
        //dealDeck.deal();

        dealDeck = new Deck();
        playerHand = new Hand();
        for (int i = 0; i < HAND_SIZE; i++) {
            playerHand.addCard(dealDeck.drawNextCard());
        }
    }

    public void transitionToHoldingPhase(int bet){
        if (phase == PlayPhase.BETTING) {
            p1.unholdAllCards();
            p1.payBet(bet);
            receiveBet(bet);
            deal();
            phase = PlayPhase.HOLDING;
        }
    }

    public void transitionToBettingPhase() {
        if (phase == PlayPhase.HOLDING) {
            replaceCards();
            WinType win = playerHand.calculateWin();
            p1.setCredits(p1.getCredits() + payoutWinnings(win));
            phase = PlayPhase.BETTING;
        }
    }

    private void replaceCards() {
        List<Boolean> heldCards = p1.getHeldCards();
        for (int i = 0; i < HAND_SIZE; i++) {
            if (heldCards.get(i) == Boolean.FALSE) {
                playerHand.replaceCard(i, dealDeck.drawNextCard());
            }
        }
    }

    int payWinnings() {
        int winnings = p1.getCredits();
        switch (playerHand.calculateWin()) {
            case ROYAL_FLUSH:
                System.out.println(" you won:" + WinType.ROYAL_FLUSH.getPayOut());
                //winnings = WinType.ROYAL_FLUSH.getPayOut() * p1.bet;
                break;
            case STRAIGHT_FLUSH:
                System.out.println("you won:" + WinType.STRAIGHT_FLUSH.getPayOut());
                //winnings += WinType.STRAIGHT_FLUSH.getPayOut() * p1.bet;
            case FOUR_OF_A_KIND:
                System.out.println("You won:" + WinType.FOUR_OF_A_KIND.getPayOut());
                //winnings += WinType.FOUR_OF_A_KIND.getPayOut() * p1.bet;
            case FLUSH:
                System.out.println("You won " + WinType.FOUR_OF_A_KIND.getPayOut());
                //winnings += WinType.FLUSH.getPayOut() * p1.bet;
            case STRAIGHT:
                System.out.println("You won" + WinType.STRAIGHT.getPayOut());
                //winnings += WinType.STRAIGHT.getPayOut() * p1.bet;
            case THREE_OF_A_KIND:
                System.out.println("You won" + WinType.THREE_OF_A_KIND.getPayOut());
                //winnings += WinType.THREE_OF_A_KIND.getPayOut() * p1.bet;
            case TWO_PAIRS:
                System.out.println("You won" + WinType.TWO_PAIRS.getPayOut());
                //winnings += WinType.TWO_PAIRS.getPayOut() * p1.bet;
            case JACKS_OR_BETTER:
                System.out.println("You won" + WinType.JACKS_OR_BETTER.getPayOut());
                //winnings += WinType.JACKS_OR_BETTER.getPayOut() * p1.bet;
            case NO_WIN:
                System.out.println("Sorry you lose");
        }
        return winnings;
    }

    void receiveBet(int betAmount) {
        bet = betAmount;
    }

    int payoutWinnings(WinType win){
        return bet * win.getPayOut();
    }

    //ACCESSORS


    public Player getP1() {
        return p1;
    }

    public PlayPhase getPhase() {
        return phase;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }
}