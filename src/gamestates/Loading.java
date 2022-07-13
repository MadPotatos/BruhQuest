package gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class Loading implements Statemethods{
	private GamePanel gp;
    int counter = 0;
	public Loading(GamePanel gp) {
        this.gp = gp;
      
    }
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		drawLoading(g2);
	}
    private void drawLoading(Graphics2D g2) {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.getTempMap();
            gp.player.worldX = gp.tileSize * gp.eHandler.getTempCol();
            gp.player.worldY = gp.tileSize * gp.eHandler.getTempRow();
            int preEventX = gp.player.worldX;
            int preEventY = gp.player.worldY;

            gp.eHandler.setPreviousEventX(preEventX);
            
            gp.eHandler.setPreviousEventY(preEventY);
            
        }

    }

}
