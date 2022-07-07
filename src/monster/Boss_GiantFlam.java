package monster;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;
import utilz.LoadSave;

public class Boss_GiantFlam extends Monster {
    private GamePanel gp;

    public Boss_GiantFlam(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("GiantFlam");
        setAniSpeed(6);
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 290;
//        solidArea.x = 3;
//        solidArea.y = 18;
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;        
//        solidArea.width = 32;
//        solidArea.height = 32;
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 60;
		solidArea.height = 60;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
        loadAnimations();

    }

    public void loadAnimations() {
        BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.BOSS_GIANTFLAM);
        UtilityTool uTool = new UtilityTool();
        BufferedImage[][] animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = imgWalk.getSubimage(i * 50, 0, 50, 50);
                animations[j][i] = uTool.scaleImage(animations[j][i], 2 * gp.tileSize, 2 * gp.tileSize);
            }
        }
        setAnimations(animations);
    }

}
