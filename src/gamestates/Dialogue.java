package gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Dialogue implements Statemethods{
	private GamePanel gp;
	private String currentDialogue;
	public Dialogue(GamePanel gp) {
        this.gp = gp;
        
    }
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		drawdialogueScreen(g2);
	}
	public void drawdialogueScreen(Graphics2D g2) {
        // WINDOW
		currentDialogue = gp.ui.currentDialogue;
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height,g2);
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
        for (String line : currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }

    }
    private void drawSubWindow(int x, int y, int width, int height,Graphics2D g2) {
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


}
