package entity.npc;

import main.GamePanel;

import utilz.LoadSave;

import entity.Entity;

public class NPC_oldman extends Entity {
    private GamePanel gp;

    public NPC_oldman(GamePanel gp) {
        super(gp);
        this.gp = gp;
        setName("Oldman");
        direction = "down";
        setType(LoadSave.TYPE_NPC);
        speed = 0;
        avatar = LoadSave.setup("/NPC/oldman/oldman_avatar", gp.tileSize, gp.tileSize);
        loadAnimations(LoadSave.NPC_OLDMAN);
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "In front of you is the gate \nleading to the dungeon.";
        dialogues[1] = "You will find your answer in \nit.";
        dialogues[2] = "Please enter if you are really \nbrave!";
    }

    public void speak() {
        super.speak();
        gp.ui.npc = this;
    }

}