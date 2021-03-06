package entity.item.obstacle;

import entity.item.Item;
import main.GamePanel;
import utilz.*;

public class Chest extends Item_obstacle {
    GamePanel gp;
    Item loot;
    boolean opened = false;

    public Chest(GamePanel gp, Item loot) {
        super(gp);
        this.gp = gp;
        this.loot = loot;
        setType(LoadSave.TYPE_OBSTACLE);
        setName("Chest");
        setImage(LoadSave.setup("/Objects/chest", gp.tileSize, gp.tileSize));
        setImage2(LoadSave.setup("/Objects/chest_opened", gp.tileSize, gp.tileSize));
        down1 = LoadSave.setup("/Objects/chest", gp.tileSize, gp.tileSize);
        setCollision(true);
        solidArea.x = 0;
        solidArea.y = 10;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact() {
        Gamestate.state = Gamestate.INFORM;
        if (opened == false) {
            gp.playSE(3);
            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and find a\n" + loot.getName() + "!");
            if (gp.player.inventory.size() == gp.player.maxInventorySize) {
                sb.append("\nYour inventory is full.");
            } else {
                gp.player.inventory.add(loot);
                sb.append("\nYou obtained " + loot.getName() + ".");
                down1 = getImage2();
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        } else {
            gp.ui.currentDialogue = "The chest is empty.";
        }
    }
}