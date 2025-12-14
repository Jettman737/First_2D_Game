package Main;

import Entity.Enemy;
import Entity.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale; //64x64
    public final int maxScreenColumns = 16;
    public final int maxScreenRows = 12;
    public final int screenwidth = tileSize * maxScreenColumns;
    public final int screenheight = tileSize * maxScreenRows;

    //WORLD SETTINGS
    public final int maxWorldColumns = 50;
    public final int maxWorldRows = 50;
    public final int worldwidth = tileSize * maxWorldColumns;
    public final int worldheight = tileSize * maxWorldRows;

    //FPS
    int FPS = 60;

    TileManager TileM = new TileManager(this);
    KeyHandler Keys =  new KeyHandler();
    Thread GameThread;
    public Player player = new Player(this, Keys);
    private final ArrayList<Enemy> Enemies = new ArrayList<>();


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(Keys);
        this.setFocusable(true);
    }

    public void startGameThread() {
        GameThread = new Thread(this);
        GameThread.start();
    }

    @Override
    public void run() {
        spawnEnemies(24,29);
        spawnEnemies(22,27);
        double drawInterval = (double) 1000000000/ this.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int runningFPS = 0, FPS = 0;

        while(GameThread != null) {
            FPS++;
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                runningFPS ++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS:" + FPS);
                System.out.println("Running FPS:" + runningFPS);
                FPS = 0;
                runningFPS = 0;
                timer = 0;
            }
        }
    }


    public void update() {
        player.update();
        for (Enemy enemy : Enemies) {
            enemy.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        TileM.draw(g2);
        player.drawEntity(g2, this);
        for (Enemy enemy : Enemies) {
            enemy.drawEntity(g2, this);
        }
        g2.dispose();
    }

    public void spawnEnemies(int tileX, int tileY) {
        Enemies.add(new Enemy(tileX, tileY, this, player));
    }
}
