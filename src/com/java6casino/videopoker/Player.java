package com.java6casino.videopoker;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final int HAND_SIZE = 5;

    //FIELDS
    private String name;
    private int credits = 100;
    private int bet;
    private List<Boolean> heldCards = new ArrayList<>();
    private Scanner scanner;


    //CONSTRUCTOR

    public Player(String name) {
        setName(name);
        Hand playerHand = new Hand();
        unHoldAllCards();


    }

    // Business Methods

    void unHoldAllCards() {
        heldCards.clear();
        for (int i = 0; i < HAND_SIZE; i++) {
            heldCards.add(Boolean.FALSE);
        }
    }

    public void placeBet() throws InputMismatchException {
        System.out.println(" Please place your bet");  // request the player to enter bet amount

        try {
            bet = scanner.nextInt();
            // check to so see if player can cover bet
            if (bet > credits) {
                System.out.println("You don't have enough to cover. please try again"); //
                placeBet();
            }
            if (bet <= 0) {
                System.out.println("Please place a valid bet");
                placeBet();
            } else {
                credits -= bet;
            }
        }
        // catch the exception when player does not use numbers for bet
        catch (InputMismatchException e) {
            System.out.println("Please input numbers only only");
            placeBet();
        }

    }

    void payBet(int betAmount){
        if (betAmount <= getCredits() ) {
            setCredits(getCredits() - betAmount);
        }
    }


    public void holdCard(int cardPosition) {
        heldCards.set(cardPosition, !heldCards.get(cardPosition));
    }


    //ACCESSORS

    public int getCredits() {
        return credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Boolean> getHeldCards() {
        return heldCards;
    }

    // Setters
    void setCredits(int newCreditAmount) {
        credits = newCreditAmount;
    }
}//End of Class