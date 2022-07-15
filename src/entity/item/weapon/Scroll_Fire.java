package entity.item.weapon;

import entity.item.Item;
import main.GamePanel;
import utilz.LoadSave;
import entity.projectile.*;

public class Scroll_Fire extends Item {
    GamePanel gp;
    Projectile projectile;

    public Scroll_Fire(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_SCROLL);
        setName("Fire Scroll");
        down1 = LoadSave.setup("/Objects/ScrollFire", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Shoot a Fire Shuriken");
        projectile = new Shuriken(gp);
    }

    public Projectile getProjectile() {
        return projectile;
    }

}
