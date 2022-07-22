package entity.item.consumable;

import entity.Entity;

import main.GamePanel;
import utilz.*;

public class HealingPotion extends Item_consumable {
    private GamePanel gp;

    public HealingPotion(GamePanel gp) {

        super(gp);
        this.gp = gp;
        setValue(5);
        setType(LoadSave.TYPE_CONSUMABLE);
        setName("Healing Potion");
        down1 = LoadSave.setup("/Objects/healingPotion", gp.tileSize, gp.tileSize);
        setDescription("[" + getName() + "]\n" + "Can be used to heal\n" + getValue() + " HP.");
        setPrice(10);
    }

    public boolean use(Entity entity) {
        Gamestate.state = Gamestate.INFORM;
        gp.ui.currentDialogue = "You used a " + getName() + ".";
        entity.life += getValue();
        if (gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
        return true;
    }
}
