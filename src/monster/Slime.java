package monster;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;
import utilz.LoadSave;

public class Slime extends Monster {
    private GamePanel gp;

    public Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("Slime");
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 290;
        solidArea.width = 42;
        solidArea.height = 30;
        loadAnimations();
    }


    public void loadAnimations() {
		BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.MONSTER_SLIME);
		UtilityTool uTool = new UtilityTool();
		BufferedImage[][] animations = new BufferedImage[4][4];
		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = imgWalk.getSubimage(j * 16, i * 16, 16, 16);
				animations[j][i] = uTool.scaleImage(animations[j][i], gp.tileSize, gp.tileSize);
			}
		}
		setAnimations(animations);
	}
    public void damageReaction() {
        setActionLockCounter(0);
        direction = gp.player.direction;
    }
}
