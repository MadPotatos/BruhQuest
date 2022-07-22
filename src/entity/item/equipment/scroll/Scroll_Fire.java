package entity.item.equipment.scroll;
import main.GamePanel;
import utilz.LoadSave;
import entity.projectile.*;

public class Scroll_Fire extends Scroll {
    GamePanel gp;

    public Scroll_Fire(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_SCROLL);
        setName("Fire Scroll");
        down1 = LoadSave.setup("/Objects/ScrollFire", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Shoot a Fire Shuriken");
        setProjectile(new Shuriken(gp));
    }

}
