package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Inform extends PaintUI{
	
	public Inform(GamePanel gp) {
        super(gp);
        //this.currentDialogue = currentDialogue;
    }
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		drawPopUpMessage(g2);
	}
	private void drawPopUpMessage(Graphics2D g2) {

		// WINDOW
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height,g2);
        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        g2.setColor(Color.black);
        for (String line : gp.ui.currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }       

    }
}
