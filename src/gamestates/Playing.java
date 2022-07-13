package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.item.Heart;
import entity.item.Item;
import entity.item.Mana;
import main.GamePanel;

public class Playing implements Statemethods {
    private GamePanel gp;
    BufferedImage heart_full, heart_half, heart_empty, mana_full, mana_empty;

//    ArrayList<String> message = new ArrayList<String>();
//    ArrayList<Integer> messageCounter = new ArrayList<Integer>();
    public Playing(GamePanel gp) {
        this.gp = gp;
        Item heart = new Heart(gp);
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_empty = heart.getImage3();
        Item mana = new Mana(gp);
        mana_full = mana.getImage();
        mana_empty = mana.getImage2();

    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        drawPlayerLife(g2);
        drawMessage(g2);
    }

    private void drawPlayerLife(Graphics2D g2) {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        // DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;

        }
        // RESET
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;

        }

        // DRAW MAX MANA
        x = (gp.tileSize / 2);
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.maxMana) {
            g2.drawImage(mana_empty, x, y, null);
            i++;
            x += 35;
        }
        // DRAW MANA
        x = (gp.tileSize / 2);
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(mana_full, x, y, null);
            i++;
            x += 35;
        }

    }

    private void drawMessage(Graphics2D g2) {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
        for (int i = 0; i < gp.ui.message.size(); i++) {
            if (gp.ui.message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(gp.ui.message.get(i), messageX + 2, messageY + 2);

                g2.setColor(new Color(214, 214, 214));
                g2.drawString(gp.ui.message.get(i), messageX, messageY);

                int counter = gp.ui.messageCounter.get(i) + 1; // messageCounter ++;
                gp.ui.messageCounter.set(i, counter);
                messageY += 50;

                if (gp.ui.messageCounter.get(i) > 120) {
                	gp.ui.message.remove(i);
                	gp.ui.messageCounter.remove(i);

                }

            }
        }
    }

}
