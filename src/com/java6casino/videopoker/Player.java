package com.java6casino.videopoker;

import java.util.InputMismatchException;
import java.util.Scanner;

class Player {
    //FIELDS
    String name;
    private int credits = 100;
    int bet;
    private Scanner scanner;



    //CONSTRUCTOR

    public Player(String name){
        setName(name);

        Hand playerHand = new Hand();


    }


    public void placeBet() throws InputMismatchException{
        System.out.println(" Please place your bet");  // request the player to enter bet amount

        try{
            bet = scanner.nextInt();
              // check to so see if player can cover bet
            if (bet > credits) {
                System.out.println("You don't have enough to cover. please try again"); //
                placeBet();
            }
            if (bet <= 0){
                System.out.println("Please place a valid bet");
                placeBet();
            }
            else{
                credits -= bet;
            }
            }
          // catch the exception when player does not use numbers for bet
        catch (InputMismatchException e){
            System.out.println("Please input numbers only only");
            placeBet();
        }

        }


    void holdCards() {


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


}//End of Class