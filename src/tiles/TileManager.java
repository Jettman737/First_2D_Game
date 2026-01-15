package tiles;

import Main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gP;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gP) {
        this.gP = gP;
        tiles = new Tile[20];
        mapTileNum = new int[gP.maxWorldColumns][gP.maxWorldRows];
        getTileImage();
        loadMap("/Maps/WorldMap1");
    }

    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Water.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Straight.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Straight.png"));
            tiles[3].rotation = 90;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Straight.png"));
            tiles[4].rotation = 180;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Straight.png"));
            tiles[5].rotation = 270;

            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Corner_out.png"));

            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Corner_out.png"));
            tiles[7].rotation = 90;

            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Corner_out.png"));
            tiles[8].rotation = 180;

            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Corner_out.png"));
            tiles[9].rotation = 270;

            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Corner_in.png"));

            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Corner_in.png"));
            tiles[11].rotation = 90;

            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Corner_in.png"));
            tiles[12].rotation = 180;

            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand_Corner_in.png"));
            tiles[13].rotation = 270;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName) {
        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gP.maxWorldColumns && row < gP.maxWorldRows) {
                String line = br.readLine();

                while (col < gP.maxWorldColumns) {
                    String[] Numbers = line.split("   ");
                    int num = Integer.parseInt(Numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gP.maxWorldColumns) {
                    col = 0;
                    row++;
                }
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;
        while (worldCol < gP.maxWorldColumns && worldRow < gP.maxWorldRows) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gP.tileSize;
            int worldY = worldRow * gP.tileSize;
            double screenX = worldX - gP.player.Worldx + gP.player.ScreenX;
            double screeny = worldY - gP.player.Worldy + gP.player.ScreenY;

            Tile tile = tiles[tileNum];

            if (tile.rotation != 0) {
                drawRotatedImage(g2, tile.image, (int) screenX, (int) screeny, gP.tileSize, gP.tileSize, tile.rotation);
            } else {
                g2.drawImage(tile.image, (int) screenX, (int) screeny, gP.tileSize, gP.tileSize, null);
            }


            worldCol++;
            if(worldCol == gP.maxWorldColumns) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    private void drawRotatedImage(
            Graphics2D g2,
            Image image,
            int x,
            int y,
            int width,
            int height,
            double angleDegrees
    ) {
        double angleRadians = Math.toRadians(angleDegrees);

        // Save original transform
        AffineTransform old = g2.getTransform();

        // Rotate around tile center
        g2.rotate(
                angleRadians,
                x + width / 2.0,
                y + height / 2.0
        );

        // Draw image
        g2.drawImage(image, x, y, width, height, null);

        // Restore original transform
        g2.setTransform(old);
    }

}
