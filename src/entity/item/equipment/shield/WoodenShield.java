package entity.item.equipment.shield;


import main.GamePanel;
import utilz.LoadSave;

public class WoodenShield extends Shield {

    public WoodenShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        setType(LoadSave.TYPE_SHIELD);
        setName("Wooden Shield");
        down1 = LoadSave.setup("/Objects/shield_wood", gp.tileSize, gp.tileSize);
        setDefenseValue(1);
        setDescription("[" + getName() + "]\n" + "A simple shield");
    }

}
