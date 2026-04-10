import javax.swing.*;
import java.awt.*;

public class GameF extends JFrame {
    private GameInfoBar gameInfoBar;
    private static String gameTime = "0:00";
    public GameF(int[][] map, int pacmanSX, int pacmanSY, int ghost1SX, int ghost1SY, int ghost2SX, int ghost2SY, int ghost3SX, int ghost3SY, int ghost4SX, int ghost4SY, String mapName) {
        setTitle("PACMAN Game");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GameP gameMap = new GameP(map,pacmanSX,pacmanSY,this,ghost1SX,ghost1SY,ghost2SX,ghost2SY,ghost3SX,ghost3SY,ghost4SX,ghost4SY, mapName);

        gameInfoBar = new GameInfoBar();

        setLayout(new BorderLayout());

        add(gameMap, BorderLayout.CENTER);
        add(gameInfoBar, BorderLayout.SOUTH);

        pack();

        setVisible(true);
        setSize(1000,600);

    }

    public void updateLives(int lives) {
        gameInfoBar.setLives(lives);
    }

    public void updateScore(int score) {
        gameInfoBar.setScore(score);
    }

    public void updateTime(String time) { gameTime = time;}

    public void updateTimeLabel() {
        gameInfoBar.setTime(gameTime);
    }
}


