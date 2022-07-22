package gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import entity.item.Item;
import entity.item.consumable.Coin;
import main.GamePanel;
import utilz.*;

public class Trading extends PaintUI {
    BufferedImage coin;

    public Trading(GamePanel gp) {
        super(gp);
        Item Coin = new Coin(gp);
        coin = Coin.getImage();

    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        drawTradeScreen(g2);
    }

    private void drawTradeScreen(Graphics2D g2) {
        switch (gp.ui.subState) {
            case 0:
                tradeSelect(g2);
                break;
            case 1:
                tradeBuy(g2);
                break;
            case 2:
                tradeSell(g2);
                break;
        }
        gp.keyH.enterPressed = false;
    }

    private void tradeSelect(Graphics2D g2) {
        drawdialogueScreen(g2);
        // DRAW WINDOW
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height, g2);
        // DRAW TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        g2.setColor(Color.black);
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (gp.ui.commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                gp.ui.subState = 1;
            }
        }
        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (gp.ui.commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                gp.ui.subState = 2;
            }
        }
        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (gp.ui.commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed == true) {
                gp.ui.commandNum = 0;
                Gamestate.state = Gamestate.DIALOUE;
                gp.ui.currentDialogue = "Come again soon";
            }
        }
    }

    private void tradeBuy(Graphics2D g2) {
        g2.setColor(Color.black);
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, false, g2);
        // DRAW NPC INVENTORY
        drawInventory(gp.ui.npc, true, g2);
        g2.setColor(Color.black);
        // DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height, g2);
        g2.setColor(Color.black);
        g2.drawString("[ESC] back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        g2.setColor(Color.black);
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height, g2);
        g2.setColor(Color.black);
        g2.drawString("Coin : " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexonSlot(gp.ui.npcSlotCol, gp.ui.npcSlotRow);
        if (itemIndex < gp.ui.npc.inventory.size()) {
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height, g2);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
            int price = gp.ui.npc.inventory.get(itemIndex).getPrice();
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8 - 20, g2);
            g2.setColor(Color.black);
            g2.drawString(text, x, y + 32);

            // BUY ITEM
            if (gp.keyH.enterPressed == true) {
                if (gp.ui.npc.inventory.get(itemIndex).getPrice() > gp.player.coin) {
                    gp.ui.subState = 0;
                    Gamestate.state = Gamestate.DIALOUE;
                    gp.ui.currentDialogue = "You don't have enough coin";
                    drawdialogueScreen(g2);
                } else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                    gp.ui.subState = 0;
                    Gamestate.state = Gamestate.DIALOUE;
                    gp.ui.currentDialogue = "You can't carry more items";
                } else {
                    gp.playSE(9);
                    gp.player.coin -= gp.ui.npc.inventory.get(itemIndex).getPrice();
                    gp.player.inventory.add(gp.ui.npc.inventory.get(itemIndex));
                    if (gp.ui.npc.inventory.get(itemIndex).getType() == LoadSave.TYPE_AXE ||
                            gp.ui.npc.inventory.get(itemIndex).getType() == LoadSave.TYPE_SHIELD
                            || gp.ui.npc.inventory.get(itemIndex).getType() == LoadSave.TYPE_SWORD
                            || gp.ui.npc.inventory.get(itemIndex).getType() == LoadSave.TYPE_SCROLL
                    		) {
                        gp.ui.npc.inventory.remove(itemIndex);
                    }

                }
            }
        }
    }

    private void tradeSell(Graphics2D g2) {
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, true, g2);

        int x;
        int y;
        int width;
        int height;
        // DRAW HINT WINDOW
        x = gp.tileSize * 2;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height, g2);
        g2.setColor(Color.black);
        g2.drawString("[ESC] back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height, g2);
        g2.setColor(Color.black);
        g2.drawString("Coin : " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE WINDOW
        int itemIndex = getItemIndexonSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = (int) (gp.tileSize * 15.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height, g2);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
            int price = gp.player.inventory.get(itemIndex).getPrice() / 2;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 18 - 20, g2);
            g2.setColor(Color.black);
            g2.drawString(text, x, y + 32);

            // SELL ITEM

            if (gp.keyH.enterPressed == true) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon
                        || gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    gp.ui.commandNum = 0;
                    gp.ui.subState = 0;
                    Gamestate.state = Gamestate.DIALOUE;
                    gp.ui.currentDialogue = "You can't sell this item";
                } else {
                    gp.playSE(9);
                    gp.player.coin += price;
                    gp.player.inventory.remove(itemIndex);
                }

            }
        }

    }

    public void drawInventory(Entity entity, boolean cursor, Graphics2D g2) {
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
            slotCol = gp.ui.playerSlotCol;
            slotRow = gp.ui.playerSlotRow;
        } else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = gp.ui.npcSlotCol;
            slotRow = gp.ui.npcSlotRow;

        }

        // FRAME

        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2);

        // slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        // Draw player's items
        for (int i = 0; i < entity.inventory.size(); i++) {
            // Equiped item
            if (entity.inventory.get(i) == gp.player.currentWeapon
                    || entity.inventory.get(i) == gp.player.currentShield) {
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
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight, g2);
                for (String line : entity.inventory.get(itemIndex).getDescription().split("\n")) {
                    g2.setColor(Color.black);
                    g2.drawString(line, textX, textY);

                    textY += 32;
                }
            }
        }

    }

    public void drawdialogueScreen(Graphics2D g2) {
        // WINDOW

        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height, g2);
        x += gp.tileSize;
        y += 32;

        g2.drawImage(gp.ui.npc.avatar, x, y, (int) (gp.tileSize * 2.5), (int) (gp.tileSize * 2.5), null);
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        x += gp.tileSize * 3;
        y += 16;
        g2.drawString(gp.ui.npc.getName() + ":", x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        y += 36;
        for (String line : gp.ui.currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }

    }
}
