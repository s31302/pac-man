import javax.swing.*;
import java.awt.*;

public class BestScoreF extends JFrame {
    public BestScoreF() {
        setTitle("Best Scores");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(new Color(0,0,0));

        BestScoreP bestScorePanel = new BestScoreP();

        add(bestScorePanel);

        JLabel lblNewLabel = new JLabel("BEST SCORES");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 50));
        lblNewLabel.setForeground(new Color(233, 220, 0));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNewLabel, BorderLayout.NORTH);

        setVisible(true);
    }

}