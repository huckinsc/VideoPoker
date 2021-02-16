package com.java6casino.videopoker.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardLoader {
    private List<ImageIcon> cardImages = new ArrayList<>();

    public CardLoader(int xSize,int ySize) {
        String[] cardNames = {"cardClubsA.png", "cardClubs2.png", "cardClubs3.png", "cardClubs4.png", "cardClubs5.png", "cardClubs6.png", "cardClubs7.png",
                "cardClubs8.png", "cardClubs9.png", "cardClubs10.png", "cardClubsJ.png", "cardClubsQ.png", "cardClubsK.png",
                "cardDiamondsA.png", "cardDiamonds2.png", "cardDiamonds3.png", "cardDiamonds4.png", "cardDiamonds5.png", "cardDiamonds6.png", "cardDiamonds7.png",
                "cardDiamonds8.png", "cardDiamonds9.png", "cardDiamonds10.png", "cardDiamondsJ.png", "cardDiamondsQ.png", "cardDiamondsK.png",
                "cardHeartsA.png", "cardHearts2.png", "cardHearts3.png", "cardHearts4.png", "cardHearts5.png", "cardHearts6.png", "cardHearts7.png",
                "cardHearts8.png", "cardHearts9.png", "cardHearts10.png", "cardHeartsJ.png", "cardHeartsQ.png", "cardHeartsK.png",
                "cardSpadesA.png", "cardSpades2.png", "cardSpades3.png", "cardSpades4.png", "cardSpades5.png", "cardSpades6.png", "cardSpades7.png",
                "cardSpades8.png", "cardSpades9.png", "cardSpades10.png", "cardSpadesJ.png", "cardSpadesQ.png", "cardSpadesK.png"};

        for (String s : cardNames) {
            String fileName = null;
            try {
                fileName = "/Cards/" + s;
                Image img = ImageIO.read(getClass().getResource(fileName));
                img = img.getScaledInstance(xSize,ySize,Image.SCALE_SMOOTH);
                cardImages.add(new ImageIcon(img));
            }
            catch (Exception e){
                System.out.println("Image failed to load: " + s);
            }
        }
    }

    public ImageIcon getCardImage(int index) {
        return cardImages.get(index);
    }
}