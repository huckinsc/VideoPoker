package com.java6casino.videopoker;


//import jdk.internal.jrtfs.JrtFileAttributeView;

import java.util.ArrayList;
import java.util.List;

class Dealer{

    //FIELDS
      Rank rank;
      Suit suit;
      Deck dealDeck;
    Hand playerHand;
    Player p1;




    //CONSTRUCTORS

    public Dealer () {
        dealDeck= new Deck();
        playerHand = new Hand();
        p1 = new Player("Player");


    }


    //METHODS
    public void deal(){
        dealDeck.deal();

    }

    public  void replaceCard() {


    }


    int payWinnings(){



        int winnings = p1.getCredits();



        switch (playerHand.calculateWin()){

            case ROYAL_FLUSH:
                System.out.println(" you won:" +WinType.ROYAL_FLUSH.getPayOut());
                winnings = WinType.ROYAL_FLUSH.getPayOut()* p1.bet;
                break;


            case STRAIGHT_FLUSH:
                System.out.println("you won:" + WinType.STRAIGHT_FLUSH.getPayOut());
                winnings += WinType.STRAIGHT_FLUSH.getPayOut()*p1.bet;


            case FOUR_OF_A_KIND:
                System.out.println("You won:" + WinType.FOUR_OF_A_KIND.getPayOut());
                winnings += WinType.FOUR_OF_A_KIND.getPayOut()*p1.bet;

            case FLUSH:
                System.out.println("You won " + WinType.FOUR_OF_A_KIND.getPayOut());
                winnings += WinType.FLUSH.getPayOut()*p1.bet;

            case STRAIGHT:
                System.out.println("You won" + WinType.STRAIGHT.getPayOut());
                winnings += WinType.STRAIGHT.getPayOut()*p1.bet;

            case THREE_OF_A_KIND:
                System.out.println("You won"+ WinType.THREE_OF_A_KIND.getPayOut());
                winnings += WinType.THREE_OF_A_KIND.getPayOut()*p1.bet;

            case TWO_PAIRS:
                System.out.println("You won" + WinType.TWO_PAIRS.getPayOut());
                winnings +=  WinType.TWO_PAIRS.getPayOut()*p1.bet;

            case JACKS_OR_BETTER:
                System.out.println("You won" + WinType.JACKS_OR_BETTER.getPayOut());
                winnings += WinType.JACKS_OR_BETTER.getPayOut() * p1.bet;
            case NO_WIN:
                System.out.println("Sorry you lose");


        }


        return winnings;


    }


    //ACCESSORS



}