package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaults();
        getPlayerImage();
        ScreenX = gp.screenwidth/2.0 - (gp.tileSize/2.0);
        ScreenY = gp.screenheight/2.0 - (gp.tileSize/2.0);
    }

    public void setDefaults() {
        Worldx = gp.tileSize * 24;
        Worldy = gp.tileSize * 27;
        speed = 4;
        direction_x = "left";
        direction_y = "";
    }

    public void getPlayerImage() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/Player/Up.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/Player/Left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Player/Right.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Player/Down.png"));
            Left_Up = ImageIO.read(getClass().getResourceAsStream("/Player/Left-Up.png"));
            Right_Up = ImageIO.read(getClass().getResourceAsStream("/Player/Right-Up.png"));
            Left_Down = ImageIO.read(getClass().getResourceAsStream("/Player/Left-Down.png"));
            Right_Down = ImageIO.read(getClass().getResourceAsStream("/Player/Right-Down.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        upMove = keyH.upPressed ? 1 : 0;
        downMove = keyH.downPressed ? 1 : 0;
        leftMove = keyH.leftPressed ? 1 : 0;
        rightMove = keyH.rightPressed ? 1 : 0;
        moveEntity();
    }
}