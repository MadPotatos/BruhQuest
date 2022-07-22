package entity.npc;

import entity.Entity;
import entity.item.consumable.HealingPotion;
import entity.item.consumable.Key;
import entity.item.equipment.shield.SteelShield;
import entity.item.equipment.weapon.Axe;
import entity.item.equipment.weapon.BeginnerSword;
import main.GamePanel;
import utilz.*;

public class NPC_merchant extends Entity implements Speakable{
    GamePanel gp;

    public NPC_merchant(GamePanel gp) {
        super(gp);
        this.gp = gp;
        // TODO Auto-generated constructor stub
        setName("Merchant");
        setType(LoadSave.TYPE_NPC);
        direction = "down";
        speed = 0;
        avatar = LoadSave.setup("/NPC/merchant/merchant_avatar", gp.tileSize, gp.tileSize);
        loadAnimations(LoadSave.NPC_MERCHANT);
        setDialogue();
        setItems();
    }

    public void setDialogue() {
        dialogues[0] = "Welcome customer.\nWhat can I do for you?";
    }

    public void setItems() {
        inventory.add(new HealingPotion(gp));
        inventory.add(new BeginnerSword(gp));
        inventory.add(new Axe(gp));
        inventory.add(new Key(gp));
        inventory.add(new SteelShield(gp));
    }

    public void speak() {
        super.speak();
        Gamestate.state = Gamestate.TRADING;
        gp.ui.npc = this;
    }
}
