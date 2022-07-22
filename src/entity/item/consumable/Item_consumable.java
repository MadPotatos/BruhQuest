package entity.item.consumable;

import entity.Entity;
import entity.item.Item;
import main.GamePanel;

public abstract class Item_consumable extends Item{
	private int value;

	public Item_consumable(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	public abstract boolean use(Entity entity);
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
