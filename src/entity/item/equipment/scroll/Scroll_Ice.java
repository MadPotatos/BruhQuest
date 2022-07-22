package entity.item.weapon;

import entity.item.Item;
import main.GamePanel;
import utilz.LoadSave;
import entity.projectile.*;

public class Scroll_Ice extends Item {
    GamePanel gp;

    public Scroll_Ice(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_SCROLL);
        setName("Ice Scroll");
        down1 = LoadSave.setup("/Objects/ScrollIce", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Shoot an ice blast");
        setProjectile(new EnergyBall(gp));
    }

}
