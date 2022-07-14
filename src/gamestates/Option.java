package gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import main.GamePanel;

public class Option extends PaintUI {

    // private int gp.ui.subState = 0;

    public Option(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g) {
        // TODO Auto-generated method stub
        drawOptionsScreen(g);
    }

    private void drawOptionsScreen(Graphics2D g2) {

        g2.setFont(g2.getFont().deriveFont(30F));
        // SUB WINDOW
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);
        switch (gp.ui.subState) {
            case 0:
                topOptions(frameX, frameY, g2);
                break;
            case 1:
                fullScreenNotification(frameX, frameY, g2);
                break;
            case 2:
                control(frameX, frameY, g2);
                break;
            case 3:
                endGameConfirmation(frameX, frameY, g2);
                break;
        }
        gp.keyH.enterPressed = false;
    }

    private void topOptions(int frameX, int frameY, Graphics2D g2) {
        int textX;
        int textY;
        // TITLE
        g2.setColor(Color.black);
        String text = "Options";
        textX = getXforCenteredText(text, g2);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if (gp.ui.commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                if (gp.fullscreenOn == false) {

                    gp.fullscreenOn = true;
                } else if (gp.fullscreenOn == true) {
                    gp.fullscreenOn = false;
                }
                gp.ui.subState = 1;
            }

        }

        // MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (gp.ui.commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }
        // SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (gp.ui.commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }
        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (gp.ui.commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.ui.subState = 2;
                gp.ui.commandNum = 0;
            }
        }
        // QUIT GAME
        textY += gp.tileSize;
        g2.drawString("Quit game", textX, textY);
        if (gp.ui.commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.ui.subState = 3;
                gp.ui.commandNum = 0;

            }
        }
        // BACK
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (gp.ui.commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.playState;
                gp.ui.commandNum = 0;
            }
        }
        // FULL SCREEN CHECKBOX
        textX = frameX + gp.tileSize * 5;
        textY = frameY + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullscreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);

        }
        // MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.getVolumeScale();
        g2.fillRect(textX, textY, volumeWidth, 24);
        // SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.getVolumeScale();
        g2.fillRect(textX, textY, volumeWidth, 24);

        try {
            gp.config.saveConfig();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void fullScreenNotification(int frameX, int frameY, Graphics2D g2) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;
        g2.setColor(Color.black);

        gp.ui.currentDialogue = "The change will \n take effect after \n restarting the game.";
        for (String line : gp.ui.currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        // BACK
        textY += gp.tileSize * 6;
        g2.drawString("Back", textX, textY);
        if (gp.ui.commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.ui.subState = 0;
            }

        }

    }

    private void control(int frameX, int frameY, Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(28F));
        g2.setColor(Color.black);
        int textX;
        int textY;
        // TITLE
        String text = "Control";
        textX = getXforCenteredText(text, g2);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Character Detail", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 1.5;
        g2.drawString("Back", textX, textY);
        if (gp.ui.commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.playSE(9);
                gp.ui.subState = 0;
                gp.ui.commandNum = 3;
            }
        }

    }

    private void endGameConfirmation(int frameX, int frameY, Graphics2D g2) {
        int textX = frameX + (int) (gp.tileSize * 1.5);
        int textY = frameY + gp.tileSize * 3;
        g2.setColor(Color.black);
        gp.ui.currentDialogue = "Are you sure you \n want to quit?";
        for (String line : gp.ui.currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        // YES
        String text = "Yes";
        textX = getXforCenteredText(text, g2);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (gp.ui.commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.ui.subState = 0;
                gp.playSE(9);
                gp.gameState = gp.titleState;
                gp.stopMusic();
                gp.playMusic(0);
            }
        }
        // NO
        text = "No";
        textX = getXforCenteredText(text, g2);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (gp.ui.commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.ui.subState = 0;
                gp.playSE(9);
                gp.ui.commandNum = 4;
            }
        }

    }

}
