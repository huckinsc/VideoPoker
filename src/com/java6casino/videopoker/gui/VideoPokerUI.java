package com.java6casino.videopoker.gui;

import com.java6casino.videopoker.controllers.VideoPokerGUIController;
import com.java6casino.videopoker.util.CardLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class VideoPokerUI extends JFrame{
    // Finals
    private final int FRAME_X_SIZE = 400;
    private final int FRAME_Y_SIZE = 525;
    private final int CARD_X_OFFSET1 = 25;
    private final int CARD_X_OFFSET2 = 75;
    private final int CARD_Y_OFFSET = 325;
    private final int HOLD_LABEL_X_SIZE = 50;
    private final int HOLD_LABEL_X_OFFSET1 = 25;
    private final int HOLD_LABEL_X_OFFSET2 = 75;
    private final int LABEL_Y_SIZE = 25;
    private final int HOLD_LABEL_Y_OFFSET = 300;
    private final int PRIZE_LABEL_X_OFFSET = 25;
    private final int PRIZE_LABEL_Y_OFFSET = 50;
    private final int PRIZE_LABEL_X_SIZE = 350;
    private final int DEAL_BUTTON_SIZE = 75;
    private final int DEAL_BUTTON_X_OFFSET = 300;
    private final int DEAL_BUTTON_Y_OFFSET = 410;
    private final int PLAYER_CREDITS_LABEL_X_OFFSET = 75;
    private final int PLAYER_CREDITS_LABEL_Y_OFFSET = 410;
    private final int PLAYER_CREDITS_LABEL_X_SIZE = 75;
    private final int CREDITS_LABEL_X_OFFSET = 25;
    private final int CREDITS_LABEL_X_SIZE = 50;
    private final int BET_BUTTON_SIZE = 40;
    private final int BET_BUTTON_X_OFFSET = 175;
    private final int BET_BUTTON_Y_OFFSET = 410;

    private final int CARD_X_SIZE = 50;
    private final int CARD_Y_SIZE = 75;

    private final String[] prizeLabelStrings = {
            "Royal Flush.............................................................250",
            "Straight Flush...........................................................50",
            "Four of a Kind...........................................................25",
            "Full House..................................................................9",
            "Flush..........................................................................6",
            "Straight......................................................................4",
            "Three of a Kind...........................................................3",
            "Two Pairs....................................................................2",
            "Pair of Jacks of Better..................................................1"
    };

    // Fields
    VideoPokerGUIController controller = new VideoPokerGUIController();

    private JButton[] cardButtons = {null,null,null,null,null};
    private JLabel[] holdLabels = {null,null,null,null,null};
    private JLabel[] prizeLabels = {null,null,null,null,null,null,null,null,null};
    private CardLoader cardLoader;
    private JButton dealButton;
    private JLabel playerCreditLabel;
    private JLabel playerBetLabel;
    private JButton[] betButtons = {null,null,null,null};

    // Constructors
    public VideoPokerUI(String title) {
        super(title);
        setLocation(200,200);
        setSize(FRAME_X_SIZE, FRAME_Y_SIZE);
        setResizable(false);
        setLayout(null);

        cardLoader = new CardLoader(CARD_X_SIZE,CARD_Y_SIZE);

        addCardButtons();
        addCardHeldLabels();
        addPrizeLabels();

        dealButton = new JButton("Deal");
        dealButton.setBounds(DEAL_BUTTON_X_OFFSET,DEAL_BUTTON_Y_OFFSET,DEAL_BUTTON_SIZE,DEAL_BUTTON_SIZE);
        dealButton.addActionListener(new HandleDealButtonClick());
        add(dealButton);

        playerCreditLabel = new JLabel("100",SwingConstants.RIGHT);
        playerCreditLabel.setBounds(PLAYER_CREDITS_LABEL_X_OFFSET,PLAYER_CREDITS_LABEL_Y_OFFSET,
                                    PLAYER_CREDITS_LABEL_X_SIZE,LABEL_Y_SIZE);
        add(playerCreditLabel);

        JLabel creditLabel = new JLabel("Credits:",SwingConstants.LEFT);
        creditLabel.setBounds(CREDITS_LABEL_X_OFFSET,PLAYER_CREDITS_LABEL_Y_OFFSET,CREDITS_LABEL_X_SIZE,LABEL_Y_SIZE);
        add(creditLabel);

        playerBetLabel = new JLabel("1",SwingConstants.RIGHT);
        playerBetLabel.setBounds(PLAYER_CREDITS_LABEL_X_OFFSET,PLAYER_CREDITS_LABEL_Y_OFFSET + 30,
                PLAYER_CREDITS_LABEL_X_SIZE,LABEL_Y_SIZE);
        add(playerBetLabel);

        JLabel betLabel = new JLabel("Bet:",SwingConstants.LEFT);
        betLabel.setBounds(CREDITS_LABEL_X_OFFSET,PLAYER_CREDITS_LABEL_Y_OFFSET + 30,CREDITS_LABEL_X_SIZE,LABEL_Y_SIZE);
        add(betLabel);

        addBetButtons();


    }

    // Methods
    private void addCardButtons(){
        HandleCardButtonClick handleCardButtonClick = new HandleCardButtonClick();
        for (int i = 0; i < cardButtons.length; i++){
            JButton card = new JButton();
            card.setBounds((i * CARD_X_OFFSET2) + CARD_X_OFFSET1, CARD_Y_OFFSET,CARD_X_SIZE, CARD_Y_SIZE);
            card.setIcon(cardLoader.getCardImage(i));
            card.addActionListener(handleCardButtonClick);
            add(card);
            cardButtons[i] = card;
        }

    }

    private void addCardHeldLabels() {
        for (int i = 0; i < holdLabels.length; i++){
            JLabel label = new JLabel("HELD", SwingConstants.CENTER);
            label.setBounds((i * HOLD_LABEL_X_OFFSET2) + HOLD_LABEL_X_OFFSET1,HOLD_LABEL_Y_OFFSET,
                    HOLD_LABEL_X_SIZE,LABEL_Y_SIZE);
            label.setBackground(Color.RED);
            label.setOpaque(true);
            label.setVisible(false);
            add(label);
            holdLabels[i] = label;
        }
    }

    private void addPrizeLabels(){
        for (int i = 0; i < prizeLabelStrings.length; i++){
            JLabel label = new JLabel(prizeLabelStrings[i]);
            label.setBounds(PRIZE_LABEL_X_OFFSET,(i * LABEL_Y_SIZE) + PRIZE_LABEL_Y_OFFSET,
                    PRIZE_LABEL_X_SIZE,LABEL_Y_SIZE);
            label.setOpaque(false);
            label.setBackground(Color.GREEN);
            add(label);
            prizeLabels[i] = label;
        }
    }

    private void addBetButtons() {
        // +1 Button
        HandleBetButtonClick handleBetButtonClick = new HandleBetButtonClick();
        JButton temp = new JButton("+1");
        temp.addActionListener(handleBetButtonClick);
        temp.setBounds(BET_BUTTON_X_OFFSET,BET_BUTTON_Y_OFFSET,BET_BUTTON_SIZE,BET_BUTTON_SIZE);
        add(temp);
        betButtons[0] = temp;

        // -1 Button
        temp = new JButton("-1");
        temp.addActionListener(handleBetButtonClick);
        temp.setBounds(BET_BUTTON_X_OFFSET,BET_BUTTON_Y_OFFSET + 40,BET_BUTTON_SIZE,BET_BUTTON_SIZE);
        add(temp);
        betButtons[1] = temp;

        // +10 Button
        temp = new JButton("+10");
        temp.addActionListener(handleBetButtonClick);
        temp.setBounds(BET_BUTTON_X_OFFSET + 40,BET_BUTTON_Y_OFFSET,BET_BUTTON_SIZE,BET_BUTTON_SIZE);
        add(temp);
        betButtons[2] = temp;

        // -10 Button
        temp = new JButton("-10");
        temp.addActionListener(handleBetButtonClick);
        temp.setBounds(BET_BUTTON_X_OFFSET + 40,BET_BUTTON_Y_OFFSET + 40,BET_BUTTON_SIZE,BET_BUTTON_SIZE);
        add(temp);
        betButtons[3] = temp;
    }

    private void clearPrizeHighlight(){
        for (JLabel l : prizeLabels){
            l.setOpaque(false);
        }
    }

    // Listener Classes
    private class HandleCardButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int requestedHold = 0;
            for (int i = 0; i < 5; i++) {
                if (e.getSource() == cardButtons[i]){
                    //holdLabels[i].setVisible(!holdLabels[i].isVisible());
                    requestedHold = i;
                    break;
                }
            }
            controller.placeHold(requestedHold);
            List<Boolean> list = controller.getHolds();
            for (int i = 0; i < list.size(); i++) {
                holdLabels[i].setVisible(list.get(i));
            }
        }
    }

    private class HandleDealButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Deal button pressed...");
            List<Integer> cardList = controller.getHand();
            for (int i = 0; i < cardList.size(); i++) {
                cardButtons[i].setIcon(cardLoader.getCardImage(cardList.get(i)));
            }

            int handVal = controller.getHandValue();
            clearPrizeHighlight();
            if (handVal < 9) {
                prizeLabels[handVal].setOpaque(true);
            }
            repaint();
        }
    }

    private class HandleBetButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int buttonIndex = -1;
            for (int i = 0; i < betButtons.length; i++) {
                if (e.getSource() == betButtons[i]){
                    buttonIndex = i;
                    break;
                }
            }
            int newValue = Integer.parseInt(playerBetLabel.getText());
            switch (buttonIndex){
                case 0:
                    newValue += 1;
                    break;
                case 1:
                    newValue -= 1;
                    break;
                case 2:
                    newValue += 10;
                    break;
                case 3:
                    newValue -= 10;
                    break;
            }
            if (controller.changeBet(newValue)) {
                playerBetLabel.setText(Integer.toString(newValue));
            }
        }
    }
}