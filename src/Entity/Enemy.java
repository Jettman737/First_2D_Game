package Entity;

import static Main.Calculations.*;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Enemy extends Entity {
    public double randomMoveTimer;
    public double idleWaitTime = Double.MAX_VALUE;
    Player player;
    private double tileRange = 4;

    public Enemy(int X, int Y, GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        collisionBox = new Rectangle(8,16,48,48);

        setDefaults(X, Y);
        getEnemyImage();
    }

    public void setDefaults(int tilex, int tiley) {
        this.Worldx = tilex * gp.tileSize;
        this.Worldy = tiley * gp.tileSize;
        ScreenX = Worldx + player.ScreenX;
        ScreenY = Worldy - player.Worldy + player.ScreenY;
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

    public void Idle(){
        randomMoveTimer += gp.FIXED_DELTA;
        if(randomMoveTimer>=1.0){
            upMove = downMove = leftMove = rightMove = 0;
            idleWaitTime = Math.random()*2 + 1.5;
        }
        if (randomMoveTimer >= idleWaitTime) { // every 1 second
            randomMoveTimer = 0;

            moveRandom();
        }
    }

    public void update() {
        if (withinRange(this, player, tileRange)) {
            upMove = player.Worldy - Worldy < -speed ? 1 : 0;
            downMove = player.Worldy - Worldy > speed ? 1 : 0;
            leftMove = player.Worldx - Worldx < -speed ? 1 : 0;
            rightMove = player.Worldx - Worldx > speed ? 1 : 0;
            randomMoveTimer = 0;
        }

        else {
            Idle();
        }
        moveEntity();
        ScreenX = Worldx - player.Worldx + player.ScreenX;
        ScreenY = Worldy - player.Worldy + player.ScreenY;
    }
}