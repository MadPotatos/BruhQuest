package entity.item.obstacle;

import java.awt.image.BufferedImage;

import entity.item.Item;
import main.GamePanel;
import utilz.*;

public class Portal extends Item {
    GamePanel gp;
    Item loot;
    boolean opened = false;

    public Portal(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_OBSTACLE);
        setName("Portal");

        setCollision(true);
        solidArea.x = gp.tileSize;
        solidArea.y = 10;
        solidArea.width = 32;
        solidArea.height = 128;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        loadAnimations();
    }

    public void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.POTAL);
        UtilityTool uTool = new UtilityTool();
        BufferedImage[][] animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, 0, 64, 64);
                animations[j][i] = uTool.scaleImage(animations[j][i], 3 * gp.tileSize, 3 * gp.tileSize);
            }
        }
        setAnimations(animations);
    }

    public void interact() {
        Gamestate.state = Gamestate.WINNER;
        gp.stopMusic();
        gp.playSE(4);
    }
}