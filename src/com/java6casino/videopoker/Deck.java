package com.java6casino.videopoker;

import java.util.ArrayList;
import java.util.Collections;

class Deck {
    private ArrayList deck;

      //Ctors
    public  Deck() {
        this.deck = new ArrayList();
        for (int i = 0; i < 13; i++) {
            Rank r = Rank.values()[i];

            for (int j = 0; j < 4; j++) {
                Card card = new Card(r, Suit.values()[j]);
                this.deck.add(card);
            }//end of for loop
        }// end of loop

        Collections.shuffle(deck);
        System.out.println(deck);
    }




}//End of class