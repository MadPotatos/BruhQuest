package entity.item.obstacle;

import entity.item.Item;
import main.GamePanel;

public abstract class Item_obstacle extends Item{
	public Item_obstacle(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	public abstract void interact();
}
