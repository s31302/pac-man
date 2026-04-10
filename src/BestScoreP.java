import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;


public class BestScoreP extends JPanel {
    public BestScoreP() {

    setLayout(new GridLayout(5, 1));
        add(createScrollPane("../Projekt/src/scoreXS", "Score for Map XS"));
        add(createScrollPane("../Projekt/src/scoreS", "Score for Map S"));
        add(createScrollPane("../Projekt/src/scoreM", "Score for Map M"));
        add(createScrollPane("../Projekt/src/scoreL", "Score for Map L"));
        add(createScrollPane("../Projekt/src/scoreXL", "Score for Map XL"));
    }

    private JScrollPane createScrollPane(String fileName, String title) {
       JList<ScoreSerializable> scoreList = new JList<>();
        scoreList.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreList.setForeground(new Color(233, 220, 0));
        scoreList.setBackground(new Color(0, 0, 0));

        DefaultListModel<ScoreSerializable> listModel = new DefaultListModel<>();
        List<ScoreSerializable> scores = readScoresFromFile(fileName);
        scores.sort(new ScoreComparator());

        for (ScoreSerializable score : scores) {
            listModel.addElement(score);
        }

        scoreList.setModel(listModel);

        JScrollPane scrollPane = new JScrollPane(scoreList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(title));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        return scrollPane;
    }

    private List<ScoreSerializable> readScoresFromFile(String fileName) {
        List<ScoreSerializable> scores = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            scores = (List<ScoreSerializable>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return scores;
    }
}
