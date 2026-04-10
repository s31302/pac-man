import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pacman extends KeyAdapter {
    private final GameP gameP;

    public Pacman(GameP gameP) {
        this.gameP = gameP;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            gameP.setPacmanDirection(2);
        } else if (key == KeyEvent.VK_RIGHT) {
            gameP.setPacmanDirection(1);
        } else if (key == KeyEvent.VK_UP) {
            gameP.setPacmanDirection(3);
        } else if (key == KeyEvent.VK_DOWN) {
            gameP.setPacmanDirection(4);
        }
    }
}
