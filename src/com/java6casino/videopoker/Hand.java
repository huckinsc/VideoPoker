package com.java6casino.videopoker;

import java.util.*;
import java.util.stream.Collectors;

public class Hand {
    // Fields
    List<Card>hand = new ArrayList<>();

    // Methods
    public void addCard(Card card){
        if (hand.size() < 5){
            hand.add(card);
        }
        else {
            // TODO: Throw exception.
        }
    }

    public void replaceCard(int index, Card newCard) throws IllegalArgumentException{
        if (index >= 0 && index < hand.size()) {
            hand.set(index,newCard);
        }
        else {
            throw new IllegalArgumentException("Invalid Index: Index should be 0 to hand-size - 1");
        }
    }

    public WinType calculateWin(){
        WinType result = WinType.NO_WIN;
        boolean isSameSuit;

        List<Card> handCopy = new ArrayList<>();
        handCopy.addAll(hand);
        handCopy.sort((card1, card2) -> Integer.compare(card1.getRank().getRankValue(),card2.getRank().getRankValue()));
        isSameSuit = checkSuits(handCopy);
        Map<Rank, Integer> rankCounts = calculateRankCounts(handCopy);

        if (isSameSuit && isInSequence(handCopy)){
            if (isHighStraight(handCopy)) {
                result = WinType.ROYAL_FLUSH;
            }
            else {
                result = WinType.STRAIGHT_FLUSH;
            }
        }
        else if (rankCounts.containsValue(4)){
            result = WinType.FOUR_OF_A_KIND;
        }
        else if (rankCounts.containsValue(3) && rankCounts.containsValue(2)) {
            result = WinType.FULL_HOUSE;
        }
        else if (isSameSuit){
            result = WinType.FLUSH;
        }
        else if (isInSequence(handCopy)) {
            result = WinType.STRAIGHT;
        }
        else if (rankCounts.containsValue(3)){
            result = WinType.THREE_OF_A_KIND;
        }
        else if (checkForTwoPair(rankCounts.values())){
            result = WinType.TWO_PAIRS;
        }
        else if (rankCounts.containsValue(2)) {
            for (Map.Entry<Rank,Integer> e : rankCounts.entrySet()) {
                if (e.getValue() == 2) {
                    if (e.getKey().getRankValue() >= Rank.JACK.getRankValue()) {
                        result = WinType.JACKS_OR_BETTER;
                    }
                    break;
                }
            }
        }
        return result;
    }

    boolean checkSuits(List<Card> hand){
        Map<Suit, Integer> suitCounts = new HashMap<>();
        for (Card card : hand){
            if (suitCounts.containsKey(card.getSuit())) {
                Integer count = suitCounts.get(card.getSuit()) + 1;
                suitCounts.put(card.getSuit(),count);
            }
            else {
                suitCounts.put(card.getSuit(),1);
            }
        }
        return (suitCounts.size() == 1);
    }

    Map<Rank,Integer> calculateRankCounts(List<Card> hand){
        Map<Rank,Integer> map = new HashMap<>();
        for (Card card : hand){
            if (map.containsKey(card.getRank())){
                Integer count = map.get(card.getRank()) + 1;
                map.put(card.getRank(),count);
            }
            else {
                map.put(card.getRank(),1);
            }
        }
        return map;
    }

    boolean checkForTwoPair(Collection<Integer> list){
        int twoCount = 0;
        for (Integer i : list){
            if (i == 2){
                twoCount++;
            }
        }
        return twoCount == 2;
    }

    boolean isInSequence(List<Card> hand) {
        boolean result = false;
        if (calculateRankCounts(hand).size() == hand.size()){
            if ((hand.get(hand.size()-1).rank.getRankValue() - hand.get(0).rank.getRankValue()) == hand.size()-1) {
                result = true;
            }
            else if (isHighStraight(hand)) {
                result = true;
            }
        }
        return result;
    }

    boolean isHighStraight(List<Card> hand){
        boolean result = false;
        if (calculateRankCounts(hand).size() == hand.size()){
            if (hand.get(0).rank == Rank.ACE && hand.get(4).rank == Rank.KING &&
                    (hand.get(4).rank.getRankValue() - hand.get(1).rank.getRankValue()) == 3){
                result = true;
            }
        }
        return result;
    }

    // Getters and Setters

    public List<Card> getHand() {
        return hand;
    }
}