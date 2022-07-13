package gamestates;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import entity.item.Coin;
import entity.item.Item;
import main.GamePanel;

public class Trading implements Statemethods{
	private GamePanel gp;
	int subState = 0;
    public int commandNum = 0;
    BufferedImage coin;
	public Trading(GamePanel gp) {
	        this.gp = gp;
	        Item Coin = new Coin(gp);
	        coin = Coin.getImage();
	        
	}
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		//drawTradeScreen(g2);
	}
	
}
