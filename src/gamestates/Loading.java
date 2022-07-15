package gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import utilz.*;

public class Loading extends PaintUI {

    public Loading(GamePanel gp) {
        super(gp);
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        drawLoading(g2);
    }

    private void drawLoading(Graphics2D g2) {
        gp.ui.counter++;
        g2.setColor(new Color(0, 0, 0, gp.ui.counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (gp.ui.counter == 50) {
            gp.ui.counter = 0;
            Gamestate.state = Gamestate.PLAYING;
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
