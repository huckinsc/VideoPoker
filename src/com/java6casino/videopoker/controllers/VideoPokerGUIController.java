package com.java6casino.videopoker.controllers;

import com.java6casino.videopoker.Card;
import com.java6casino.videopoker.Dealer;
import com.java6casino.videopoker.PlayPhase;
import com.java6casino.videopoker.WinType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VideoPokerGUIController {
    private Dealer system;
    PlayPhase phase;

    public VideoPokerGUIController() {
        system = new Dealer();
        phase = system.getPhase();
    }

    // request to system to place a hold on a specified card
    public void placeHold(int card){
        if (phase == PlayPhase.HOLDING) {
            system.getP1().holdCard(card);
        }
    }

    // get and return to the UI the holds list
    public List<Boolean> getHolds(){
        //System.out.println("In controller: getHolds");
        return system.getP1().getHeldCards();
    }


    // Get hand data for updating the UI
    public List<Integer> getHand(){
        System.out.println("In controller: getHand");
        List<Integer> result = new ArrayList<>();
        for (Card c : system.getPlayerHand().getHand()) {
            int cardValue = translateCardToPositionReference(c);
            result.add(cardValue);
        }
        return result;
    }

    // Request to check if the bet amount is valid
    public boolean changeBet(int betAmount){
        System.out.println("In controller: changeBet");
        if (phase == PlayPhase.BETTING) {
            int playerCredits = system.getP1().getCredits();
            if (betAmount > 0 && betAmount <= playerCredits){
                return true;
            }
        }
        return false;
    }

    // Process the Deal/Draw event.  Retuns player credits as these events can affect that value.
    public int processDealDrawEvent(int betAmount) {
        if (phase == PlayPhase.BETTING) {
            system.transitionToHoldingPhase(betAmount);
        }
        else if (phase == PlayPhase.HOLDING) {
            system.transitionToBettingPhase();
        }
        phase = system.getPhase();
        return system.getP1().getCredits();
    }

    public int getPlayPhase(){
        return (phase == PlayPhase.BETTING)? 0 : 1;
    }

    // get hand value
    public int getHandValue() {
        WinType type = system.getPlayerHand().calculateWin();
        int result;
        switch (type){
            case ROYAL_FLUSH:
                result = 0;
                break;
            case STRAIGHT_FLUSH:
                result = 1;
                break;
            case FOUR_OF_A_KIND:
                result = 2;
                break;
            case FULL_HOUSE:
                result = 3;
                break;
            case FLUSH:
                result = 4;
                break;
            case STRAIGHT:
                result = 5;
                break;
            case THREE_OF_A_KIND:
                result = 6;
                break;
            case TWO_PAIRS:
                result = 7;
                break;
            case JACKS_OR_BETTER:
                result = 8;
                break;
            default:
                result = 9;
        }
        return result;
    }

    private int translateCardToPositionReference(Card card) {
        int result = 0;
        switch (card.getSuit()){
            case CLUBS:
                result = 0 * 13;
                break;
            case DIAMONDS:
                result = 1 * 13;
                break;
            case HEARTS:
                result = 2 * 13;
                break;
            case SPADES:
                result = 3 * 13;
                break;
        }
        result += card.getRank().getRankValue();
        return (result -1);
    }
}