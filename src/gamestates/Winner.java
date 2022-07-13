package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Winner implements Statemethods{
	GamePanel gp;
	public Winner(GamePanel gp) {
        this.gp = gp;
	}
      
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		drawWinScreen(g2);
	}
	private void drawWinScreen(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90f));
        int x;
        int y;
        String text;

        text = "Congratulation!!!";
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text,g2);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        // text
        g2.setColor(Color.green);
        g2.drawString(text, x - 4, y - 4);
        // text
        g2.setColor(Color.green);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
        text = "You found your way back home";
        x = getXforCenteredText(text,g2);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        // return to title screen
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        text = "Press Enter to return";
        x = getXforCenteredText(text,g2);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
    }
	public int getXforCenteredText(String text,Graphics2D g2) {
	    int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	    int x = gp.screenWidth / 2 - length / 2;
	    return x;
	}
}
