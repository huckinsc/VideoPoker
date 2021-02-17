package com.java6casino.videopoker.gui;

import com.java6casino.videopoker.Dealer;
import com.java6casino.videopoker.controllers.VideoPokerGUIController;

public class Main {
    public static void main(String[] args) {
        Dealer system = new Dealer();
        VideoPokerGUIController controller = new VideoPokerGUIController(system);
        VideoPokerUI videoPokerUI = new VideoPokerUI("Video Poker", controller);
        videoPokerUI.setVisible(true);
    }
}