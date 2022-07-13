package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Trading implements Statemethods{
	private GamePanel gp;
	int subState = 0;
    public int commandNum = 0;
	public Trading(GamePanel gp) {
	        this.gp = gp;
	        
	}
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

//    private void drawTradeScreen() {
//        switch (subState) {
//            case 0:
//                tradeSelect();
//                break;
//
//            case 1:
//                tradeBuy();
//                break;
//            case 2:
//                tradeSell();
//                break;
//        }
//        gp.keyH.enterPressed = false;
//    }
//
//    private void tradeSelect(Graphics2D g2) {
//        drawdialogueScreen();
//        // DRAW WINDOW
//        int x = gp.tileSize * 15;
//        int y = gp.tileSize * 4;
//        int width = gp.tileSize * 3;
//        int height = (int) (gp.tileSize * 3.5);
//        drawSubWindow(x, y, width, height);
//        // DRAW TEXT
//        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
//        g2.setColor(Color.black);
//        x += gp.tileSize;
//        y += gp.tileSize;
//        g2.drawString("Buy", x, y);
//        if (commandNum == 0) {
//            g2.drawString(">", x - 24, y);
//            if (gp.keyH.enterPressed == true) {
//                subState = 1;
//            }
//        }
//        y += gp.tileSize;
//        g2.drawString("Sell", x, y);
//        if (commandNum == 1) {
//            g2.drawString(">", x - 24, y);
//            if (gp.keyH.enterPressed == true) {
//                subState = 2;
//            }
//        }
//        y += gp.tileSize;
//        g2.drawString("Leave", x, y);
//        if (commandNum == 2) {
//            g2.drawString(">", x - 24, y);
//            if (gp.keyH.enterPressed == true) {
//                commandNum = 0;
//                gp.gameState = gp.dialogueState;
//                gp.ui.currentDialogue = "Come again soon";
//            }
//        }
//    }
//
//    private void tradeBuy(Graphics2D g2) {
//        g2.setColor(Color.black);
//        // DRAW PLAYER INVENTORY
//        drawInventory(gp.player, false);
//        // DRAW NPC INVENTORY
//        drawInventory(npc, true);
//        g2.setColor(Color.black);
//        // DRAW HINT WINDOW
//        int x = gp.tileSize * 2;
//        int y = gp.tileSize * 9;
//        int width = gp.tileSize * 6;
//        int height = gp.tileSize * 2;
//        drawSubWindow(x, y, width, height);
//        g2.setColor(Color.black);
//        g2.drawString("[ESC] back", x + 24, y + 60);
//
//        // DRAW PLAYER COIN WINDOW
//        g2.setColor(Color.black);
//        x = gp.tileSize * 12;
//        y = gp.tileSize * 9;
//        width = gp.tileSize * 6;
//        height = gp.tileSize * 2;
//        drawSubWindow(x, y, width, height);
//        g2.setColor(Color.black);
//        g2.drawString("Coin : " + gp.player.coin, x + 24, y + 60);
//
//        // DRAW PRICE WINDOW
//        int itemIndex = getItemIndexonSlot(npcSlotCol, npcSlotRow);
//        if (itemIndex < npc.inventory.size()) {
//            x = (int) (gp.tileSize * 5.5);
//            y = (int) (gp.tileSize * 5.5);
//            width = (int) (gp.tileSize * 2.5);
//            height = gp.tileSize;
//            drawSubWindow(x, y, width, height);
//            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
//            int price = npc.inventory.get(itemIndex).getPrice();
//            String text = "" + price;
//            x = getXforAlignToRightText(text, gp.tileSize * 8 - 20);
//            g2.setColor(Color.black);
//            g2.drawString(text, x, y + 32);
//
//            // BUY ITEM
//            if (gp.keyH.enterPressed == true) {
//                if (npc.inventory.get(itemIndex).getPrice() > gp.player.coin) {
//                    subState = 0;
//                    gp.gameState = gp.dialogueState;
//                    gp.ui.currentDialogue = "You don't have enough coin";
//                    drawdialogueScreen();
//                } else if (gp.player.inventory.size() == gp.player.maxInventorySize) {
//                    subState = 0;
//                    gp.gameState = gp.dialogueState;
//                    gp.ui.currentDialogue = "You can't carry more items";
//                } else {
//                    gp.playSE(9);
//                    gp.player.coin -= npc.inventory.get(itemIndex).getPrice();
//                    gp.player.inventory.add(npc.inventory.get(itemIndex));
//
//                }
//            }
//        }
//    }
//
//    private void tradeSell(Graphics2D g2) {
//        // DRAW PLAYER INVENTORY
//        characterSate.drawInventory(gp.player, true,g2);
//
//        int x;
//        int y;
//        int width;
//        int height;
//        // DRAW HINT WINDOW
//        x = gp.tileSize * 2;
//        y = gp.tileSize * 9;
//        width = gp.tileSize * 6;
//        height = gp.tileSize * 2;
//        drawSubWindow(x, y, width, height);
//        g2.setColor(Color.black);
//        g2.drawString("[ESC] back", x + 24, y + 60);
//
//        // DRAW PLAYER COIN WINDOW
//        x = gp.tileSize * 12;
//        y = gp.tileSize * 9;
//        width = gp.tileSize * 6;
//        height = gp.tileSize * 2;
//        drawSubWindow(x, y, width, height);
//        g2.setColor(Color.black);
//        g2.drawString("Coin : " + gp.player.coin, x + 24, y + 60);
//
//        // DRAW PRICE WINDOW
//        int itemIndex = getItemIndexonSlot(characterSate.playerSlotCol, characterSate.playerSlotRow);
//        if (itemIndex < gp.player.inventory.size()) {
//            x = (int) (gp.tileSize * 15.5);
//            y = (int) (gp.tileSize * 5.5);
//            width = (int) (gp.tileSize * 2.5);
//            height = gp.tileSize;
//            drawSubWindow(x, y, width, height);
//            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);
//            int price = gp.player.inventory.get(itemIndex).getPrice() / 2;
//            String text = "" + price;
//            x = getXforAlignToRightText(text, gp.tileSize * 18 - 20);
//            g2.setColor(Color.black);
//            g2.drawString(text, x, y + 32);
//
//            // SELL ITEM
//
//            if (gp.keyH.enterPressed == true) {
//                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon
//                        || gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
//                    commandNum = 0;
//                    subState = 0;
//                    gp.gameState = gp.dialogueState;
//                    gp.ui.currentDialogue = "You can't sell this item";
//                } else {
//                    gp.playSE(9);
//                    gp.player.coin += price;
//                    gp.player.inventory.remove(itemIndex);
//                }
//
//            }
//        }
//
//    }
//
//

}
