package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.sqrt;

public abstract class Entity {
    public double speed, Worldx, Worldy, ScreenX, ScreenY;
    int upMove, downMove, leftMove, rightMove;
    double x_move, y_move;
    public BufferedImage left, right, up, down, Right_Up, Right_Down, Left_Up, Left_Down;
    public String direction_x, direction_y;

    public void moveEntity(){
        x_move = rightMove - leftMove;
        y_move = downMove - upMove;
        if(!(x_move == 0 && y_move == 0)){
            direction_x = x_move == 0? "":  x_move == 1 ? "right" : "left";
            direction_y = y_move == 0? "":  y_move == 1 ? "down" : "up";
        }


        if(x_move !=0 && y_move!=0){
            x_move /= sqrt(2);
            y_move /= sqrt(2);
        }

        Worldy += y_move*speed;
        Worldx += x_move*speed;
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
}
