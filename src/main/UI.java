package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.BasicStroke;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import entity.Entity;
import entity.item.*;
import gamestates.Character;
import gamestates.GameOver;

import gamestates.Inform;
import gamestates.Loading;
import gamestates.Menu;
import gamestates.Option;
import gamestates.Playing;
import gamestates.Winner;

public class UI {
    GamePanel gp;
    Font MineCraft;
    BufferedImage coin;
    Image bg;
    Graphics2D g2;

    ArrayList<String> message = new ArrayList<String>();
    ArrayList<Integer> messageCounter = new ArrayList<Integer>();

    public Menu menuState;
    public Playing playingState;
    public Character characterSate;
    public Option optionState;
    public Winner winState;
    public GameOver gameOverState;
    public Loading loadingState;
    public Inform informState;

    public String currentDialogue = "";
    public int commandNum = 0;

    public int playerSlotCol = 0;
    public int playerSlotRow = 0;

    public int npcSlotCol = 0;
    public int npcSlotRow = 0;

    int subState = 0;
    int counter = 0;
    public Entity npc;

    public UI(GamePanel gp) {
        this.gp = gp;
        initClasses();
        try {
            InputStream is = getClass().getResourceAsStream("/Font/determination.ttf");
            MineCraft = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // CREATE HUD OBJECT
        Item Coin = new Coin(gp);
        coin = Coin.getImage();
        try {
            bg = new ImageIcon(
                    getClass().getResource("/ui/bg.gif")).getImage();
        } catch (Exception e) {
        }

    }

    private void initClasses() {
        menuState = new Menu(gp);
        playingState = new Playing(gp);
        optionState = new Option(gp);

        winState = new Winner(gp);
        gameOverState = new GameOver(gp);
        loadingState = new Loading(gp);

        informState = new Inform(gp);

        characterSate = new Character(gp);

    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(MineCraft);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            menuState.draw(g2);
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            playingState.draw(g2);
            drawMessage();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawdialogueScreen();
        }
        // INFORM STATE
        if (gp.gameState == gp.informState) {
             informState.draw(g2);
        }
        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            optionState.draw(g2);
        }
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            gameOverState.draw(g2);
        }
        // LOADING STATE
        if (gp.gameState == gp.loadingState) {
            loadingState.draw(g2);
        }
        // TRADING STATE
        if (gp.gameState == gp.tradingState) {
            drawTradeScreen();
        }
        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            characterSate.draw(g2);
        }
        // Winner
        if (gp.gameState == gp.winState) {
            winState.draw(g2);
        }
    }

    private void drawTradeScreen() {
        switch (subState) {
            case 0:
                tradeSelect();
                break;

            case 1:
                tradeBuy();
                break;
            case 2:
                tradeSell();
                break;
        }
        gp.keyH.enterPressed = false;
    }

    private void tradeSelect() {
        drawdialogueScreen();
        // DRAW WINDOW
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);
        // DRAW TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        g2.setColor(Color.black);
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                subState = 1;
            }
        }
        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                subState = 2;
            }
        }
        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                gp.ui.currentDialogue = "Come again soon";
            }
        }
    }

    private void tradeBuy() {
        g2.setColor(Color.black);
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        // DRAW NPC INVENTORY
        drawInventory(npc, true);
        g2.setColor(Color.black);
        // DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.setColor(Color.black);
        g2.drawString("[ESC] back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        g2.setColor(Color.black);
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.setColor(Color.black);
        g2.drawString("Coin : " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexonSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
            int price = npc.inventory.get(itemIndex).getPrice();
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8 - 20);
            g2.setColor(Color.black);
            g2.drawString(text, x, y + 32);

            // BUY ITEM
            if (gp.keyH.enterPressed == true) {
                if (npc.inventory.get(itemIndex).getPrice() > gp.player.coin) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "You don't have enough coin";
                    drawdialogueScreen();
                } else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "You can't carry more items";
                } else {
                    gp.playSE(9);
                    gp.player.coin -= npc.inventory.get(itemIndex).getPrice();
                    gp.player.inventory.add(npc.inventory.get(itemIndex));

                }
            }
        }
    }

    private void tradeSell() {
        // DRAW PLAYER INVENTORY
        characterSate.drawInventory(gp.player, true, g2);

        int x;
        int y;
        int width;
        int height;
        // DRAW HINT WINDOW
        x = gp.tileSize * 2;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.setColor(Color.black);
        g2.drawString("[ESC] back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.setColor(Color.black);
        g2.drawString("Coin : " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexonSlot(characterSate.playerSlotCol, characterSate.playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = (int) (gp.tileSize * 15.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
            int price = gp.player.inventory.get(itemIndex).getPrice() / 2;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 18 - 20);
            g2.setColor(Color.black);
            g2.drawString(text, x, y + 32);

            // SELL ITEM

            if (gp.keyH.enterPressed == true) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon
                        || gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    gp.ui.currentDialogue = "You can't sell this item";
                } else {
                    gp.playSE(9);
                    gp.player.coin += price;
                    gp.player.inventory.remove(itemIndex);
                }

            }
        }

    }

    private void drawInventory(Entity entity, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;
        if (entity == gp.player) {
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;

        }

        // FRAME

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        // Draw player's items
        for (int i = 0; i < entity.inventory.size(); i++) {
            // Equiped item
            if (entity.inventory.get(i) == entity.currentWeapon
                    || entity.inventory.get(i) == entity.currentShield) {
                g2.setColor(new Color(255, 148, 24));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14 || i == 19) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // CURSOR
        if (cursor == true) {
            int cursorX = slotXstart + slotCol * slotSize;
            int cursorY = slotYstart + slotRow * slotSize;
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
            // draw cursor
            g2.setColor(new Color(193, 120, 35));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // description frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;

            // description
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));

            int itemIndex = getItemIndexonSlot(slotCol, slotRow);

            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for (String line : entity.inventory.get(itemIndex).getDescription().split("\n")) {
                    g2.setColor(Color.black);
                    g2.drawString(line, textX, textY);

                    textY += 32;
                }
            }
        }

    }

    public int getItemIndexonSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + slotRow * 5;
        return itemIndex;
    }

    private void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);

                g2.setColor(new Color(214, 214, 214));
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // messageCounter ++;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 120) {
                    message.remove(i);
                    messageCounter.remove(i);

                }

            }
        }
    }



    public void drawdialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);
        x += gp.tileSize;
        y += 32;

        g2.drawImage(npc.avatar, x, y, (int) (gp.tileSize * 2.5), (int) (gp.tileSize * 2.5), null);
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        x += gp.tileSize * 3;
        y += 16;
        g2.drawString(npc.getName() + ":", x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        y += 36;
        for (String line : currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }

    }

    private void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(255, 255, 255, 210);
        g2.setColor(c);
        g2.setColor(new Color(255, 255, 255, 200));

        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(156, 100, 3);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        c = new Color(255, 148, 24);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 10, y + 10, width - 20, height - 20, 15, 15);

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXforAlignToRightText(String text, int tailX) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

}
