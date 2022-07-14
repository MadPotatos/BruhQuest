package gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public abstract class PaintUI {
	public GamePanel gp;
	public PaintUI(GamePanel gp) {
        this.gp = gp;
      
    }
	public abstract void draw(Graphics2D g2);
	
    public void drawSubWindow(int x, int y, int width, int height, Graphics2D g2) {
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

	public int getXforCenteredText(String text,Graphics2D g2) {
	    int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
	    int x = gp.screenWidth / 2 - length / 2;
	    return x;
	}
	
    public int getXforAlignToRightText(String text, int tailX, Graphics2D g2) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    public int getItemIndexonSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + slotRow * 5;
        return itemIndex;
    }

}
