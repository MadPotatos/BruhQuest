package entity.item.equipment.scroll;

import main.GamePanel;
import utilz.LoadSave;
import entity.projectile.*;

public class Scroll_Rock extends Scroll {
    GamePanel gp;

    public Scroll_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_SCROLL);
        setName("Rock Scroll");
        down1 = LoadSave.setup("/Objects/ScrollRock", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Throw a rock");
        setProjectile(new Rock(gp));
    }

}
