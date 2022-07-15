package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Dialogue extends PaintUI{

	public Dialogue(GamePanel gp) {
        super(gp);
    }
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		drawdialogueScreen(g2);
	}
	public void drawdialogueScreen(Graphics2D g2) {
        // WINDOW

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
        for (String line : gp.ui.currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }

    }
}
