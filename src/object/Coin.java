package object;

import java.awt.image.BufferedImage;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;
import utilz.LoadSave;

public class Coin extends Item {
    private GamePanel gp;

    public Coin(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setType(LoadSave.TYPE_PICKUPONLY);
        setName("Coin");
        setValue(1);
        setAniSpeed(4);
        loadAnimations();
    }
    public void loadAnimations() {
        BufferedImage imgWalk = LoadSave.GetSpriteAtlas(LoadSave.COIN);
        UtilityTool uTool = new UtilityTool();
        BufferedImage[][] animations = new BufferedImage[4][4];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = imgWalk.getSubimage(i * 10, 0, 10, 10);
                animations[j][i] = uTool.scaleImage(animations[j][i],gp.tileSize/2,gp.tileSize/2);
            }
        }
        setAnimations(animations);
    }    

    public boolean use(Entity entity) {
        gp.playSE(1);
        gp.ui.addMessage("Coin +" + getValue());
        gp.player.coin += getValue();
        return true;
    }
}
