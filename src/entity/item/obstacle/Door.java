package entity.item.obstacle;

import entity.item.Item;
import main.GamePanel;
import utilz.*;

public class Door extends Item {
    GamePanel gp;

    public Door(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_OBSTACLE);
        setName("Door");
        down1 = LoadSave.setup("/Objects/door", gp.tileSize, gp.tileSize);
        setCollision(true);

        solidArea.x = 12;
        solidArea.y = 10;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void interact() {
        Gamestate.state = Gamestate.INFORM;
        gp.ui.currentDialogue = "You need a key to open the door.";
    }
}