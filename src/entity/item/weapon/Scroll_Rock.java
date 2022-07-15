package entity.item.weapon;

import entity.item.Item;
import main.GamePanel;
import utilz.LoadSave;
import entity.projectile.*;

public class Scroll_Rock extends Item {
    Projectile projectile;
    GamePanel gp;

    public Scroll_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_SCROLL);
        setName("Rock Scroll");
        down1 = LoadSave.setup("/Objects/ScrollRock", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Throw a rock");
        projectile = new Rock(gp);
    }

    public Projectile getProjectile() {
        return projectile;
    }

}
