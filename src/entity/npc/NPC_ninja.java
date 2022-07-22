package entity.npc;

import java.util.Random;

import entity.Action;
import entity.Entity;

import main.GamePanel;
import utilz.LoadSave;

public class NPC_ninja extends Entity implements Speakable, Action{
    private GamePanel gp;

    public NPC_ninja(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("Ninja");
        setType(LoadSave.TYPE_NPC);
        direction = "down";
        speed = 1;
        avatar = LoadSave.setup("/NPC/ninja/avatar", gp.tileSize, gp.tileSize);
        loadAnimations(LoadSave.NPC_NINJA);
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "You're not from around here,\n aren't you?";
        dialogues[1] = "It is safe here.";
        dialogues[2] = "You can let down your guard.";
    }

    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);
        if (getActionLockCounter() == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i < 25) {
                direction = "up";
            }
            if (i > 25 && i < 50) {
                direction = "down";
            }
            if (i > 50 && i < 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            setActionLockCounter(0);
        }
    }

    public void speak() {
        super.speak();
        gp.ui.npc = this;
    }
}