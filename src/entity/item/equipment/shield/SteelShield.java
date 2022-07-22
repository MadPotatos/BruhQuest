package entity.item.equipment.shield;


import main.GamePanel;
import utilz.LoadSave;

public class SteelShield extends Shield {

    public SteelShield(GamePanel gp) {
        super(gp);
        // TODO Auto-generated constructor stub
        setType(LoadSave.TYPE_SHIELD);
        setName("Steel Shield");
        down1 = LoadSave.setup("/Objects/shield_steel", gp.tileSize, gp.tileSize);
        setDefenseValue(2);
        setDescription("[" + getName() + "]\n" + "A shield made of\nsteel.");
    }

}
