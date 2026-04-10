import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapMenu implements ActionListener {

    JFrame newFrame = new JFrame("New Window");
    JPanel panel = new JPanel();
    JButton buttonXS = new JButton("XS");
    JButton buttonS = new JButton("S");
    JButton buttonM = new JButton("M");
    JButton buttonL = new JButton("L");
    JButton buttonXL = new JButton("XL");

    public MapMenu() {
        newFrame.setLayout(null);
        newFrame.setSize(1000, 600);
        newFrame.setTitle("PACMAN");
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setVisible(true);

        panel.setLayout(null);
        panel.setSize(1000, 600);
        panel.setBackground(Color.black);
        panel.setVisible(true);
        newFrame.add(panel);

        JLabel lblNewLabel = new JLabel("CHOOSE A MAP");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 50));
        lblNewLabel.setForeground(Color.yellow);
        int labelWidth = lblNewLabel.getPreferredSize().width + 10;
        int labelHeight = lblNewLabel.getPreferredSize().height;
        lblNewLabel.setBounds((panel.getWidth() - labelWidth) / 2, 50, labelWidth, labelHeight);
        panel.add(lblNewLabel);

        buttonXS.setBounds(200, 200, 100, 75);
        panel.add(buttonXS);
        buttonS.setBounds((panel.getWidth() - 100) / 2, 200, 100, 75);
        panel.add(buttonS);
        buttonM.setBounds(700, 200, 100, 75);
        panel.add(buttonM);
        buttonL.setBounds(325, 350, 100, 75);
        panel.add(buttonL);
        buttonXL.setBounds(575, 350, 100, 75);
        panel.add(buttonXL);

        buttonXS.addActionListener(this);
        buttonS.addActionListener(this);
        buttonM.addActionListener(this);
        buttonL.addActionListener(this);
        buttonXL.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonXS) {
            XSMap xsmap = new XSMap();
            new GameF(xsmap.getMap(), xsmap.getPacmanStartX(),xsmap.getPacmanStartY(),xsmap.getGhost1StartX(),xsmap.getGhost1StartY(),xsmap.getGhost2StartX(),xsmap.getGhost2StartY(),xsmap.getGhost3StartX(),xsmap.getGhost3StartY(),xsmap.getGhost4StartX(),xsmap.getGhost4StartY(),"XS");
        } else if (e.getSource() == buttonS) {
            SMap smap = new SMap();
            new GameF(smap.getMap(), smap.getPacmanStartX(), smap.getPacmanStartY(), smap.getGhost1StartX(),smap.getGhost1StartY(),smap.getGhost2StartX(),smap.getGhost2StartY(),smap.getGhost3StartX(),smap.getGhost3StartY(),smap.getGhost4StartX(),smap.getGhost4StartY(),"S");
        } else if (e.getSource() == buttonM) {
            MMap mmap = new MMap();
            new GameF(mmap.getMap(), mmap.getPacmanStartX(), mmap.getPacmanStartY(), mmap.getGhost1StartX(), mmap.getGhost1StartY(),mmap.getGhost2StartX(), mmap.getGhost2StartY(), mmap.getGhost3StartX(),mmap.getGhost3StartY(),mmap.getGhost4StartX(),mmap.getGhost4StartY(),"M");
        } else if (e.getSource() == buttonL) {
            LMap lmap = new LMap();
            new GameF(lmap.getMap(), lmap.getPacmanStartX(), lmap.getPacmanStartY(), lmap.getGhost1StartX(), lmap.getGhost1StartY(), lmap.getGhost2StartX(),lmap.getGhost2StartY(), lmap.getGhost3StartX(), lmap.getGhost3StartY(),lmap.getGhost4StartX(),lmap.getGhost4StartY(),"L");
        } else if (e.getSource() == buttonXL) {
            XLMap xlmap = new XLMap();
            new GameF(xlmap.getMap(), xlmap.getPacmanStartX(), xlmap.getPacmanStartY(), xlmap.getGhost1StartX(), xlmap.getGhost1StartY(),xlmap.getGhost2StartX(),xlmap.getGhost2StartY(), xlmap.getGhost3StartX(),xlmap.getGhost3StartY(),xlmap.getGhost4StartX(),xlmap.getGhost4StartY(),"XL");
        }
        newFrame.dispose();

    }
}