package com.java6casino.videopoker.controllers;

import com.java6casino.videopoker.WinType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VideoPokerGUIController {


    public VideoPokerGUIController() {


    }

    // request to system to place a hold on a specified card
    public void placeHold(int card){
        System.out.println("In controller: placeHold on " + card);

    }

    // get and return to the UI the holds list
    public List<Boolean> getHolds(){
        System.out.println("In controller: getHolds");
        return null;
    }

    // Get hand data for updating the UI
    public List<Integer> getHand(){
        System.out.println("In controller: getHand");
        List<Integer> result = new ArrayList<>();

        return result;
    }

    // Request to check if the bet amount is valid
    public boolean changeBet(int betAmount){
        System.out.println("In controller: changeBet");
        int playerCredits = 100; // get from system

        if (betAmount > 0 && betAmount <= playerCredits){
            return true;
        }
        return false;
    }

    // get hand value
    public int getHandValue() {
        WinType type;
        int result;
        type = WinType.FULL_HOUSE; // get from system
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
}