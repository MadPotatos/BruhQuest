package gamestates;

import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import main.GamePanel;
import utilz.LoadSave;

public class HowToPlay extends PaintUI{
	private Image bg;
    public HowToPlay(GamePanel gp) {
        super(gp);
        
    }
	public void draw(Graphics2D g2) {
        BufferedImage image;
        g2.drawImage(bg, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setColor(new Color(0, 0, 0, 160));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        g2.setColor(Color.white);
        String text = "KEYBOARD CONTROLS";
        int x = getXforCenteredText(text,g2);
        int y = gp.tileSize;

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // ESCAPE
        image = LoadSave.KB_B;
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        g2.drawImage(image, x, y, gp.tileSize / 2, gp.tileSize / 2, null);
        g2.setColor(Color.white);
        g2.drawString("BACK (B)", x + 30, y + 18);

        // W,A,S,D
        image = LoadSave.KB_W;
        if (gp.keyH.upPressed == true) {
            image = LoadSave.KB_W_P;
        }
        x = gp.tileSize * 2 + 6;
        y = gp.tileSize * 2;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        image = LoadSave.KB_A;
        if (gp.keyH.leftPressed == true) {
            image = LoadSave.KB_A_P;
        }
        x = gp.tileSize;
        y = gp.tileSize * 3 + 6;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        image = LoadSave.KB_S;
        if (gp.keyH.downPressed == true) {
            image = LoadSave.KB_S_P;
        }
        x = gp.tileSize * 2 + 6;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        image = LoadSave.KB_D;
        if (gp.keyH.rightPressed == true) {
            image = LoadSave.KB_D_P;
        }
        x = gp.tileSize * 3 + 12;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // ATTACK
        image = LoadSave.KB_ENTER;
        if (gp.keyH.enterPressed == true) {
            image = LoadSave.KB_ENTER_P;
            gp.keyH.enterPressed = false;
        }
        x = gp.tileSize + 16;
        y += gp.tileSize + 32;
        g2.drawImage(image, x, y, gp.tileSize * 3, gp.tileSize, null);

        // SHOOT
        image = LoadSave.KB_F;
        if (gp.keyH.shotKeyPressed == true) {
            image = LoadSave.KB_F_P;
        }
        x = gp.tileSize * 2 + 6;
        y += gp.tileSize + 32;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // Inventory
        image = LoadSave.KB_C;
        if (gp.keyH.cPressed == true) {
            image = LoadSave.KB_C_P;
        }
        y += gp.tileSize + 32;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // Settings
        x = gp.tileSize + 16;
        image = LoadSave.KB_ESC;
        if (gp.keyH.escPressed == true) {
            image = LoadSave.KB_ESC_P;
        }
        y += gp.tileSize + 32;
        g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize, null);
        // TEXT
        x += gp.tileSize * 6;
        y = gp.tileSize * 3;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36));
        g2.setColor(Color.white);
        g2.drawString(" - MOVE", x, y);
        y += gp.tileSize * 2 + 28;
        g2.drawString(" - ATTACK / INTERACT", x, y);
        y += gp.tileSize + 30;
        g2.drawString(" - INVENTORY", x, y);
        y += gp.tileSize + 30;
        g2.drawString(" - SHOOT", x, y);
        y += gp.tileSize + 30;
        g2.drawString(" - SETTING", x, y);
    }

}
