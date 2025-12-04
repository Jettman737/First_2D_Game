package Entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Enemy extends Entity {

    GamePanel gp;
    Player player;

    public Enemy(int X, int Y,GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        setDefaults(X, Y);
        getEnemyImage();
    }

    public void setDefaults(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 2;
        direction_x = "left";
        direction_y = "";
    }

    public void getEnemyImage() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/Enemy/Up.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/Enemy/Left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/Enemy/Right.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/Enemy/Down.png"));
            Left_Up = ImageIO.read(getClass().getResourceAsStream("/Enemy/Left-Up.png"));
            Right_Up = ImageIO.read(getClass().getResourceAsStream("/Enemy/Right-Up.png"));
            Left_Down = ImageIO.read(getClass().getResourceAsStream("/Enemy/Left-Down.png"));
            Right_Down = ImageIO.read(getClass().getResourceAsStream("/Enemy/Right-Down.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        upMove = player.y - y < -speed ? 1 : 0;
        downMove = player.y - y > speed ? 1 : 0;
        leftMove = player.x - x < -speed ? 1 : 0;
        rightMove = player.x - x > speed ? 1 : 0;
        moveEntity();
    }
}