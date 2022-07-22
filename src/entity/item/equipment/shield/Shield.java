package entity.item.equipment.shield;

import entity.item.Item;
import main.GamePanel;

public class Shield extends Item{
	private int defenseValue;
	public Shield(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	public int getDefenseValue() {
		return defenseValue;
	}

	public void setDefenseValue(int defenseValue) {
		this.defenseValue = defenseValue;
	}

}
