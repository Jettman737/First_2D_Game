package Main;

import Entity.Entity;

public class CollisionCheck {

    public GamePanel gamePanel;

    public CollisionCheck(GamePanel gp) {
        gamePanel = gp;
    }

    public boolean checkCollisionX(Entity e) {

        double nextX = e.Worldx + e.x_move;

        double left = nextX + e.collisionBox.x;
        double right = left + e.collisionBox.width;
        double top = e.Worldy + e.collisionBox.y;
        double bottom = top + e.collisionBox.height;

        int leftCol = (int) (left / gamePanel.tileSize);
        int rightCol = (int) (right / gamePanel.tileSize);
        int topRow = (int) (top / gamePanel.tileSize);
        int bottomRow = (int) (bottom / gamePanel.tileSize);

        return gamePanel.TileM.tiles[gamePanel.TileM.mapTileNum[leftCol][topRow]].collision ||
                gamePanel.TileM.tiles[gamePanel.TileM.mapTileNum[leftCol][bottomRow]].collision ||
                gamePanel.TileM.tiles[gamePanel.TileM.mapTileNum[rightCol][topRow]].collision ||
                gamePanel.TileM.tiles[gamePanel.TileM.mapTileNum[rightCol][bottomRow]].collision;
    }

    public boolean checkCollisionY(Entity e) {

        double nextY = e.Worldy + e.y_move;

        double left = e.Worldx + e.collisionBox.x;
        double right = left + e.collisionBox.width;
        double top = nextY + e.collisionBox.y;
        double bottom = top + e.collisionBox.height;

        int leftCol = (int)(left / gamePanel.tileSize);
        int rightCol = (int)(right / gamePanel.tileSize);
        int topRow = (int)(top / gamePanel.tileSize);
        int bottomRow = (int)(bottom / gamePanel.tileSize);

        return gamePanel.TileM.tiles[gamePanel.TileM.mapTileNum[leftCol][topRow]].collision ||
                gamePanel.TileM.tiles[gamePanel.TileM.mapTileNum[rightCol][topRow]].collision ||
                gamePanel.TileM.tiles[gamePanel.TileM.mapTileNum[leftCol][bottomRow]].collision ||
                gamePanel.TileM.tiles[gamePanel.TileM.mapTileNum[rightCol][bottomRow]].collision;
    }
}
