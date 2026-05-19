import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JButton buttonS = new JButton("START");
    JButton buttonE = new JButton("EXIT");
    JButton buttonB = new JButton("BEST SCORE");

    public Menu() {
        frame.setLayout(null);
        frame.setSize(1000, 600);
        frame.setTitle("PACMAN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.setLayout(null);
        panel.setSize(1000, 600);
        panel.setBackground(Color.black);
        panel.setVisible(true);

        JLabel lblNewLabel = new JLabel("PAC-MAN");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 150));
        lblNewLabel.setForeground(Color.yellow);
        int labelWidth = 750;
        int labelHeight = 175;
        lblNewLabel.setBounds((panel.getWidth() - labelWidth) / 2, 75, labelWidth, labelHeight);
        panel.add(lblNewLabel);


        buttonS.setBounds((panel.getWidth() - 200) / 2, 300, 200, 50);
        panel.add(buttonS);
        buttonB.setBounds((panel.getWidth() - 200) / 2 - 300, 300, 200, 50);
        panel.add(buttonB);
        buttonE.setBounds((panel.getWidth() - 200) / 2 + 300, 300, 200, 50);
        panel.add(buttonE);

        frame.add(panel);
        buttonS.addActionListener(this);
        buttonE.addActionListener(this);
        buttonB.addActionListener(this);

        buttonS.setOpaque(true);
        buttonS.setBorderPainted(false);
        buttonS.setBackground(Color.YELLOW);
        buttonS.setForeground(Color.BLACK);

        buttonE.setOpaque(true);
        buttonE.setBorderPainted(false);
        buttonE.setBackground(Color.YELLOW);
        buttonE.setForeground(Color.BLACK);

        buttonB.setOpaque(true);
        buttonB.setBorderPainted(false);
        buttonB.setBackground(Color.YELLOW);
        buttonB.setForeground(Color.BLACK);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonS) {
            MapMenu mapMenu = new MapMenu();
        } else if (e.getSource() == buttonE) {
            System.exit(0);
        } else if (e.getSource() == buttonB) {
            BestScoreF scoreP = new BestScoreF();
        }
    }
}
