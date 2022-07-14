package gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import entity.Entity;
import main.GamePanel;

public class Character extends PaintUI{
	//private GamePanel gp;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
	public Character(GamePanel gp) {
        super(gp);
      
    }
	public void draw(Graphics2D g2) {
		drawCharacterScreen(g2);
		drawInventory(gp.player, true,g2);
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

        drawSubWindow(frameX, frameY, frameWidth, frameHeight,g2);

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
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight,g2);
                for (String line : entity.inventory.get(itemIndex).getDescription().split("\n")) {
                    g2.setColor(Color.black);
                    g2.drawString(line, textX, textY);

                    textY += 32;
                }
            }
        }

    }
	
	public void drawCharacterScreen(Graphics2D g2) {

	        // CREATE A FRAME
	        final int frameX = gp.tileSize * 2;
	        final int frameY = gp.tileSize;
	        final int frameWidth = gp.tileSize * 5;
	        final int frameHeight = gp.tileSize * 10;
	        drawSubWindow(frameX, frameY, frameWidth, frameHeight,g2);

	        // TEXT
	        g2.setColor(Color.black);
	        g2.setFont(g2.getFont().deriveFont(24F));
	        int textX = frameX + 24;
	        int textY = frameY + 40;
	        final int lineHeight = 36;

	        // NAMES
	        g2.drawString("Level", textX, textY);
	        textY += lineHeight;
	        g2.drawString("Vigor", textX, textY);
	        textY += lineHeight;
	        g2.drawString("Mind", textX, textY);
	        textY += lineHeight;
	        g2.drawString("Strength", textX, textY);
	        textY += lineHeight;
	        g2.drawString("Endurance", textX, textY);
	        textY += lineHeight;
	        g2.drawString("Attack", textX, textY);
	        textY += lineHeight;

	        g2.drawString("Defense", textX, textY);
	        textY += lineHeight;

	        g2.drawString("Exp", textX, textY);
	        textY += lineHeight;
	        g2.drawString("Next level", textX, textY);
	        textY += lineHeight;

	        g2.drawString("Coin", textX, textY);
	        textY += lineHeight + 12;

	        g2.drawString("Weapon", textX, textY);
	        textY += lineHeight + 8;
	        g2.drawString("Shield", textX, textY);
	        textY += lineHeight;

	        // VALUES
	        int tailX = (frameX + frameWidth) - 30;
	        // Reset textY
	        textY = frameY + 40;
	        String value;

	        value = String.valueOf(gp.player.level);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.strength);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.endurance);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.attack);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.defense);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.exp);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.nextLevelExp);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        value = String.valueOf(gp.player.coin);
	        textX = getXforAlignToRightText(value, tailX,g2);
	        g2.drawString(value, textX, textY);
	        textY += lineHeight;

	        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 25, null);
	        textY += gp.tileSize;

	        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 25, null);

	}
}
