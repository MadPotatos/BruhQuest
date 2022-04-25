package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Door extends SuperObject {
    GamePanel gp;

    public Door(GamePanel gp) {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));
            uTool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.printStackTrace();

        }
        collision = true;
    }
}
