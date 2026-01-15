package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    GamePanel gp;

    public double speed, Worldx, Worldy, ScreenX, ScreenY;
    public int upMove, downMove, leftMove, rightMove;
    public double x_move;
    public double y_move;
    public BufferedImage left, right, up, down, Right_Up, Right_Down, Left_Up, Left_Down;
    public String direction_x, direction_y;
    public Rectangle collisionBox;


    public void moveEntity(){
        x_move = rightMove - leftMove;
        y_move = downMove - upMove;
        if(!(x_move == 0 && y_move == 0)){
            direction_x = x_move == 0? "":  x_move == 1 ? "right" : "left";
            direction_y = y_move == 0? "":  y_move == 1 ? "down" : "up";
        }


        if (x_move != 0 && y_move != 0) {
            x_move /= Math.sqrt(2);
            y_move /= Math.sqrt(2);
        }
        x_move *= speed;
        y_move *= speed;

        // X AXIS
        if (!gp.collisionCheck.checkCollisionX(this)) {
            Worldx += x_move;
        }

        // Y movement (SLIDING happens here)
        if (!gp.collisionCheck.checkCollisionY(this)) {
            Worldy += y_move;
        }
    }

    public void drawEntity(Graphics2D g2, GamePanel gp) {
        BufferedImage image = null;
        if(!direction_x.isEmpty() && !direction_y.isEmpty()){
            if (direction_x.equals("right")) {
                image = switch (direction_y) {
                    case "up" -> Right_Up;
                    case "down" -> Right_Down;
                    default -> image;
                };
            }
            else if (direction_x.equals("left")) {
                image = switch (direction_y) {
                    case "up" -> Left_Up;
                    case "down" -> Left_Down;
                    default -> image;
                };
            }

        }
        else{
            String totalMove = direction_x +direction_y;
            image = switch (totalMove) {
                case "right" -> right;
                case "left" -> left;
                case "up" -> up;
                case "down" -> down;
                default -> image;
            };
        }
        g2.drawImage(image, (int) ScreenX, (int) ScreenY, gp.tileSize, gp.tileSize, null);
    }

    public void moveRandom(){
        upMove = downMove = leftMove = rightMove = 0;

        int dir = (int)(Math.random() * 8);

        switch (dir) {
            case 0 -> upMove = 1;                    // up
            case 1 -> downMove = 1;                  // down
            case 2 -> leftMove = 1;                  // left
            case 3 -> rightMove = 1;                 // right
            case 4 -> { upMove = 1; leftMove = 1; }  // up-left
            case 5 -> { upMove = 1; rightMove = 1; } // up-right
            case 6 -> { downMove = 1; leftMove = 1; }// down-left
            case 7 -> { downMove = 1; rightMove = 1; }// down-right
        }
    }

    public void Idle(){}
}
