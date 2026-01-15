package Main;

import Entity.Entity;

import java.lang.Math;

public final class Calculations {
    static GamePanel gamePanel;
    public Calculations(GamePanel gp) {
        gamePanel = gp;
    } // prevent instantiation

    public static double distanceTo(Entity a, Entity b) {
        return Math.hypot(
                (a.Worldx + gamePanel.tileSize / 2.0) - (b.Worldx + gamePanel.tileSize / 2.0),
                (a.Worldy + gamePanel.tileSize / 2.0) - (b.Worldy + gamePanel.tileSize / 2.0)
        );
    }

    public static boolean withinRange(Entity a, Entity b, double rangeTiles) {
        return distanceTo(a, b) <= rangeTiles*gamePanel.tileSize;
    }

    public static double angleTo(Entity from, Entity to) {
        return Math.atan2(
                to.Worldy - from.Worldy,
                to.Worldx - from.Worldx
        );
    }
}
