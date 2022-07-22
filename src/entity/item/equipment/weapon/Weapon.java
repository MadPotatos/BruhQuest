package entity.item.equipment.weapon;

import entity.item.Item;
import main.GamePanel;

public abstract class Weapon extends Item{
	private int attackValue;
	public Weapon(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}
	public int getAttackValue() {
		return attackValue;
	}

	public void setAttackValue(int attackValue) {
		this.attackValue = attackValue;
	}

}
