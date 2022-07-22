package entity.item.equipment.weapon;

import entity.item.Item;
import main.GamePanel;
import utilz.LoadSave;

public class Axe extends Weapon {

    public Axe(GamePanel gp) {
        super(gp);
        setType(LoadSave.TYPE_AXE);
        setName("Axe");
        down1 = LoadSave.setup("/Objects/axe", gp.tileSize, gp.tileSize);
        setAttackValue(2);
        setDescription("[" + getName() + "]\n" + "Can be used to chop\ndown tree.");
        attackArea.width = 28;
        attackArea.height = 28;
        setPrice(100);
        knockBackPower = 10;
    }
    
}
