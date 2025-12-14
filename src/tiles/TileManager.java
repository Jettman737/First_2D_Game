package tiles;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gP;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gP) {
        this.gP = gP;
        tiles = new Tile[10];
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName){
        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gP.maxWorldColumns && row < gP.maxWorldRows) {
                String line = br.readLine();
                while(col < gP.maxWorldColumns) {
                    String[] Numbers = line.split(" ");

                    int num =  Integer.parseInt(Numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gP.maxWorldColumns){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(IOException e){
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

            g2.drawImage(tiles[tileNum].image, (int) screenX, (int) screeny, gP.tileSize, gP.tileSize, null);
            worldCol++;
            if(worldCol == gP.maxWorldColumns) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
