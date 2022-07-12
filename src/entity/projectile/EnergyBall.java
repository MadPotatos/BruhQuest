package entity.projectile;

import entity.Entity;
import main.GamePanel;
import utilz.LoadSave;

import java.awt.Color;

public class EnergyBall extends Projectile {
    private GamePanel gp;

    public EnergyBall(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("EnergyBall");
        setType(LoadSave.TYPE_PROJECTTILE);
        speed = 10;
        maxLife = 40;
        life = maxLife;
        attack = 2;
        setUseCost(1);
        alive = false;
        loadAnimations(LoadSave.PROTILE_ENERGYBALL);
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if (user.mana >= getUseCost()) {
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.mana -= getUseCost();
    }

}
