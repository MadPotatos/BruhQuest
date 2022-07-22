package entity.item.equipment.scroll;

import entity.item.Item;
import entity.projectile.Projectile;
import main.GamePanel;

public class Scroll extends Item{
	GamePanel gp;
	private Projectile projectile;
	public Scroll(GamePanel gp) {
		super(gp);
		this.gp = gp;
		// TODO Auto-generated constructor stub
	}
	public void setProjectile(Projectile projectile) {
		this.projectile = projectile;
	}

	public Projectile getProjectile() {
		return projectile;
	}
}
