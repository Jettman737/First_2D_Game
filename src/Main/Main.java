package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame GameWindow = new JFrame();
        GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameWindow.setResizable(false);
        GameWindow.setTitle("First Game");

        GamePanel GamePanel = new GamePanel();
        GameWindow.add(GamePanel);

        GameWindow.pack();

        GameWindow.setLocationRelativeTo(null);
        GameWindow.setVisible(true);

        GamePanel.startGameThread();
    }
}