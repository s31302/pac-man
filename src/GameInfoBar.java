import javax.swing.*;
import java.awt.*;

public class GameInfoBar extends JPanel {
    private JLabel livesLabel;
    private JLabel scoreLabel;
    private JLabel timeLabel;

    public GameInfoBar() {
        setLayout(new GridLayout(1, 3));
        setBackground(new Color(213, 1, 155));

        livesLabel = new JLabel("Lives: 3");
        scoreLabel = new JLabel("Score: 0");
        timeLabel = new JLabel("Time: 0:00");

        livesLabel.setForeground(new Color(255, 255, 255));
        scoreLabel.setForeground(new Color(255, 255, 255));
        timeLabel.setForeground(new Color(255, 255, 255));

        add(livesLabel);
        add(scoreLabel);
        add(timeLabel);
    }

    public void setLives(int lives) {
        livesLabel.setText("Lives: " + lives);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void setTime(String time) {
        timeLabel.setText("Time: " + time);
    }
}

