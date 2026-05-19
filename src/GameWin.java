import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {
    JFrame newFrame = new JFrame("Winning Game");
    JPanel panel = new JPanel();

    public GameWin(int score, float time, String mapName) {
        newFrame.setTitle("Winning Game");
        newFrame.setSize(1000, 600);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setVisible(true);

        panel.setLayout(null);
        panel.setSize(1000, 600);
        panel.setBackground(Color.black);

        JLabel label = new JLabel("WIN!");
        label.setFont(new Font("Arial", Font.BOLD, 100));
        label.setForeground(new Color(233, 220, 0));
        label.setBounds(410, 50, 1000, 100);
        panel.add(label);

        JLabel scoreLabel = new JLabel("Your score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(new Color(233, 220, 0));
        scoreLabel.setBounds(450, 160, 200, 30);
        panel.add(scoreLabel);

        int minutes = (int) (time / 60);
        int seconds = (int) (time % 60);
        String timeString = String.format("Time: %02d:%02d", minutes, seconds);

        JLabel timeLabel = new JLabel(timeString);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setForeground(new Color(233, 220, 0));
        timeLabel.setBounds(460, 220, 200, 30);
        panel.add(timeLabel);

        JLabel nickLabel = new JLabel("Enter your nick:");
        nickLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nickLabel.setForeground(new Color(233, 220, 0));
        nickLabel.setBounds(455, 270, 200, 20);
        panel.add(nickLabel);

        JTextField nickField = new JTextField();
        nickField.setFont(new Font("Arial", Font.PLAIN, 16));
        nickField.setBounds(420, 300, 200, 40);
        nickField.setBackground(new Color(255, 255, 255));
        nickField.setForeground(new Color(255, 79, 205));
        panel.add(nickField);

        JButton submitButton = new JButton("SAVE GAME");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBounds(450, 370, 150, 40);
        submitButton.addActionListener(e -> {
            String nick = nickField.getText().trim();
            if (!nick.isEmpty()) {
                saveScoreToFile(new ScoreSerializable(nick, score, time, mapName));
                newFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(newFrame, "Please enter your nick.");
            }
        });
        panel.add(submitButton);
        newFrame.add(panel);
        newFrame.setVisible(true);
    }

    private void saveScoreToFile(ScoreSerializable scoreSerializable) {
        File directory = new File("Scores");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = "Scores/score" + scoreSerializable.getMapName() + ".dat";
        List<ScoreSerializable> scores = readScoresFromFile(fileName);
        scores.add(scoreSerializable);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<ScoreSerializable> readScoresFromFile(String fileName) {
        List<ScoreSerializable> scores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            scores = (List<ScoreSerializable>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {}
        return scores;
    }
}