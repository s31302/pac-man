import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameP extends JPanel implements Runnable {
    public static final int TILE_SIZE = 40;
    private int lives = 3;
    private boolean gameEnded = false;
    private int[][] map = getMap();
    private int pacmanX;
    private int pacmanY;
    private int ghost1X;
    private int ghost1Y;
    private int ghost2X;
    private int ghost2Y;
    private int ghost3X;
    private int ghost3Y;
    private int ghost4X;
    private int ghost4Y;
    private final int startingPacmanX;
    private final int startingPacmanY;
    private final int startingGhost1X;
    private final int startingGhost1Y;
    private final int startingGhost2X;
    private final int startingGhost2Y;
    private final int startingGhost3X;
    private final int startingGhost3Y;
    private final int startingGhost4X;
    private final int startingGhost4Y;
    private final ImageIcon[][] pacmanImages;
    private int pacmanDirection = 1;
    private final ImageIcon[][] ghost1Images;
    private int ghost1Direction = 1;
    private final ImageIcon[][] ghost2Images;
    private int ghost2Direction = 1;
    private final ImageIcon[][] ghost3Images;
    private int ghost3Direction = 1;
    private final ImageIcon[][] ghost4Images;
    private int ghost4Direction = 1;
    public TimerThread timerThread;
    private static final int ANIMATION_DELAY = 200;
    private Thread movementThread;
    private int animationState = 1;
    private int animationGhost = 1;
    private boolean opening = true;
    private boolean isStarted = false;
    private final GameF gameFrame;
    private JLabel pacmanLabel;
    private JLabel ghost1Label;
    private JLabel ghost2Label;
    private JLabel ghost3Label;
    private JLabel ghost4Label;
    private JPanel[][] cellPanels;
    private List<int[]> dots;
    private ImageIcon dotImage;
    private int score = 0;
    private Ghost1 ghost1;
    private Ghost3 ghost3;
    private Ghost2 ghost2;
    private Ghost4 ghost4;
    String mapName;


    public GameP(int[][] map, int pacmanX, int pacmanY, GameF gameFrame, int ghost1X, int ghost1Y, int ghost2X, int ghost2Y, int ghost3X, int ghost3Y, int ghost4X, int ghost4Y, String mapName ){
        this.map = map;
        this.pacmanX = this.startingPacmanX = pacmanX;
        this.pacmanY = this.startingPacmanY = pacmanY;
        this.ghost1X = this.startingGhost1X = ghost1X;
        this.ghost1Y = this.startingGhost1Y = ghost1Y;
        this.ghost2X = this.startingGhost2X = ghost2X;
        this.ghost2Y = this.startingGhost2Y = ghost2Y;
        this.ghost3X = this.startingGhost3X = ghost3X;
        this.ghost3Y = this.startingGhost3Y = ghost3Y;
        this.ghost4X = this.startingGhost4X = ghost4X;
        this.ghost4Y = this.startingGhost4Y = ghost4Y;
        this.timerThread = new TimerThread(gameFrame);
        this.gameFrame = gameFrame;
        this.mapName = mapName;

        pacmanImages = new ImageIcon[5][4];
        for (int i = 1; i < 5; i++) {
            for (int j = 3; j > 0; j--) {
                ImageIcon pacmanImage = new ImageIcon("../Projekt/src/Images/pacman" + i + "_" + j + ".png");
                pacmanImages[i][j] = new ImageIcon(pacmanImage.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
            }
        }

        ghost1Images = new ImageIcon[5][3];
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 3; j++) {
                ImageIcon ghost1Image  = new ImageIcon("../Projekt/src/Images/ghost1_" + i + "_" + j + ".png");
                ghost1Images[i][j] = new ImageIcon(ghost1Image.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
            }
        }
        ghost2Images = new ImageIcon[5][3];
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 3; j++) {
                ImageIcon ghost2Image  = new ImageIcon("../Projekt/src/Images/ghost2_" + i + "_" + j + ".png");
                ghost2Images[i][j] = new ImageIcon(ghost2Image.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
            }
        }
        ghost3Images = new ImageIcon[5][3];
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 3; j++) {
                ImageIcon ghost3Image  = new ImageIcon("../Projekt/src/Images/ghost3_" + i + "_" + j + ".png");
                ghost3Images[i][j] = new ImageIcon(ghost3Image.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
            }
        }
        ghost4Images = new ImageIcon[5][3];
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 3; j++) {
                ImageIcon ghost4Image  = new ImageIcon("../Projekt/src/Images/ghost4_" + i + "_" + j + ".png");
                ghost4Images[i][j] = new ImageIcon(ghost4Image.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
            }
        }

        dotImage = new ImageIcon("../Projekt/src/Images/dotM.png");
        dotImage = new ImageIcon(dotImage.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));

        setLayout(new GridLayout(map.length, map[0].length));

        cellPanels = new JPanel[map.length][map[0].length];
        dots = new ArrayList<>();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBackground(map[y][x] == 1 ? new Color(255, 79, 205) : new Color(0, 0, 0));
                cellPanel.setLayout(new BorderLayout());
                cellPanels[y][x] = cellPanel;
                add(cellPanel);
                if (map[y][x] == 0) {
                    JLabel dotLabel = new JLabel(new ImageIcon(dotImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                    dotLabel.setHorizontalAlignment(JLabel.CENTER);
                    dotLabel.setVerticalAlignment(JLabel.CENTER);
                    cellPanel.add(dotLabel);
                    dots.add(new int[]{x, y});
                }
            }
        }

        pacmanLabel = new JLabel(pacmanImages[pacmanDirection][animationState]);
        cellPanels[pacmanY][pacmanX].add(pacmanLabel);

        ghost1Label = new JLabel(ghost1Images[ghost1Direction][animationGhost]);
        cellPanels[ghost1X][ghost1Y].add(ghost1Label);

        ghost2Label = new JLabel(ghost2Images[ghost2Direction][animationGhost]);
        cellPanels[ghost2X][ghost2Y].add(ghost2Label);

        ghost3Label = new JLabel(ghost3Images[ghost3Direction][animationGhost]);
        cellPanels[ghost3X][ghost3Y].add(ghost3Label);

        ghost4Label = new JLabel(ghost4Images[ghost4Direction][animationGhost]);
        cellPanels[ghost4X][ghost4Y].add(ghost4Label);

        setFocusable(true);
        addKeyListener(new Pacman(this));

        Thread animationThread = new Thread(this);
        animationThread.start();

        ghost1 = new Ghost1(this, ghost1X, ghost1Y);
        Thread ghost1Thread = new Thread(ghost1);
        ghost2 = new Ghost2(this, ghost2X, ghost2Y);
        Thread ghost2Thread = new Thread(ghost2);
        ghost3 = new Ghost3(this, ghost3X, ghost3Y);
        Thread ghost3Thread = new Thread(ghost3);
        ghost4 = new Ghost4(this, ghost4X, ghost4Y);
        Thread ghost4Thread = new Thread(ghost4);

        ghost1Thread.start();
        ghost2Thread.start();
        ghost3Thread.start();
        ghost4Thread.start();

        movementThread = new Thread(() -> {
            try {
                Thread.sleep(500);
                while (!gameEnded) {
                    if (isStarted) {
                        movePacmanLoop();
                    }
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        movementThread.start();
        timerThread.start();
    }

    private void checkForWin() {
        if (dots.isEmpty()) {
            endGameWithWin();
        }
    }
    private void endGameWithWin() {
        if (!gameEnded) {
            gameEnded = true;
            new GameWin(score, timerThread.getTimeOfGame(), mapName);
            gameFrame.dispose();
        }
    }

    private synchronized void updatePacmanPosition() {
        int newMoveX = pacmanX;
        int newMoveY = pacmanY;

        switch (pacmanDirection) {
            case 4:
                newMoveY++;
                break;
            case 3:
                newMoveY--;
                break;
            case 1:
                newMoveX++;
                break;
            case 2:
                newMoveX--;
                break;
        }

        if (isValidMove(newMoveX, newMoveY)) {
            cellPanels[pacmanY][pacmanX].remove(pacmanLabel);
            cellPanels[pacmanY][pacmanX].revalidate();
            cellPanels[pacmanY][pacmanX].repaint();

            pacmanX = newMoveX;
            pacmanY = newMoveY;

            collectDot(newMoveX, newMoveY);

            pacmanLabel.setIcon(pacmanImages[pacmanDirection][animationState]);
            cellPanels[pacmanY][pacmanX].add(pacmanLabel);
            cellPanels[pacmanY][pacmanX].revalidate();
            cellPanels[pacmanY][pacmanX].repaint();

            checkForDot();
            checkForWin();
        }
    }

    private void collectDot(int x, int y) {
        for (int i = 0; i < dots.size(); i++) {
            int[] dot = dots.get(i);
            if (dot[0] == x && dot[1] == y) {
                dots.remove(i);
                score++;
                gameFrame.updateScore(score);
                cellPanels[y][x].removeAll();
                cellPanels[y][x].revalidate();
                cellPanels[y][x].repaint();
                break;
            }
        }
    }
    private void checkForDot() {
        int[] currentPos = new int[]{pacmanX, pacmanY};
        for (int i = 0; i < dots.size(); i++) {
            if (dots.get(i)[0] == currentPos[0] && dots.get(i)[1] == currentPos[1]) {
                dots.remove(i);
                score += 10;
                gameFrame.updateScore(score);
                break;
            }
        }
    }
    private void movePacmanLoop() {
        while (true) {
            try {
                Thread.sleep(ANIMATION_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                if (isStarted && !gameEnded){
                    updatePacmanPosition();
                    checkCollisions();
                }
            }
        }
    }
    public void setPacmanDirection(int direction) {
        this.pacmanDirection = direction;
        this.isStarted = true;

    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length && map[y][x] != 1;
    }

    public int[][] getMap() {
        return map;
    }

    public synchronized void moveGhost1(int fromX, int fromY, int toX, int toY) {
        cellPanels[fromY][fromX].remove(ghost1Label);
        cellPanels[fromY][fromX].revalidate();
        cellPanels[fromY][fromX].repaint();

        ghost1X = toX;
        ghost1Y = toY;

        ghost1Label.setIcon(ghost1Images[ghost1Direction][animationGhost]);
        cellPanels[toY][toX].add(ghost1Label);
        cellPanels[toY][toX].revalidate();
        cellPanels[toY][toX].repaint();
    }

    public synchronized void moveGhost2(int fromX, int fromY, int toX, int toY) {
        cellPanels[fromY][fromX].remove(ghost2Label);
        cellPanels[fromY][fromX].revalidate();
        cellPanels[fromY][fromX].repaint();

        ghost2X = toX;
        ghost2Y = toY;

        ghost2Label.setIcon(ghost2Images[ghost2Direction][animationGhost]);
        cellPanels[toY][toX].add(ghost2Label);
        cellPanels[toY][toX].revalidate();
        cellPanels[toY][toX].repaint();
    }
    public synchronized void moveGhost3(int fromX, int fromY, int toX, int toY) {
        cellPanels[fromY][fromX].remove(ghost3Label);
        cellPanels[fromY][fromX].revalidate();
        cellPanels[fromY][fromX].repaint();

        ghost3X = toX;
        ghost3Y = toY;

        ghost3Label.setIcon(ghost3Images[ghost3Direction][animationGhost]);
        cellPanels[toY][toX].add(ghost3Label);
        cellPanels[toY][toX].revalidate();
        cellPanels[toY][toX].repaint();
    }
    public synchronized void moveGhost4(int fromX, int fromY, int toX, int toY) {
        cellPanels[fromY][fromX].remove(ghost4Label);
        cellPanels[fromY][fromX].revalidate();
        cellPanels[fromY][fromX].repaint();

        ghost4X = toX;
        ghost4Y = toY;

        ghost4Label.setIcon(ghost4Images[ghost4Direction][animationGhost]);
        cellPanels[toY][toX].add(ghost4Label);
        cellPanels[toY][toX].revalidate();
        cellPanels[toY][toX].repaint();
    }
    public synchronized void updateGhost1Image(int x, int y, int direction, int animationGhost) {
        ghost1Direction = direction;
        ghost1Label.setIcon(ghost1Images[direction][animationGhost]);
        cellPanels[y][x].revalidate();
        cellPanels[y][x].repaint();
    }
    public synchronized void updateGhost2Image(int x, int y, int direction, int animationGhost) {
        ghost2Direction = direction;
        ghost2Label.setIcon(ghost2Images[direction][animationGhost]);
        cellPanels[y][x].revalidate();
        cellPanels[y][x].repaint();
    }

    public synchronized void updateGhost3Image(int x, int y, int direction, int animationGhost) {
        ghost3Direction = direction;
        ghost3Label.setIcon(ghost3Images[direction][animationGhost]);
        cellPanels[y][x].revalidate();
        cellPanels[y][x].repaint();
    }

    public synchronized void updateGhost4Image(int x, int y, int direction, int animationGhost) {
        ghost4Direction = direction;
        ghost4Label.setIcon(ghost4Images[direction][animationGhost]);
        cellPanels[y][x].revalidate();
        cellPanels[y][x].repaint();
    }

private void checkCollisions() {
    int pacmanCenterX = pacmanX * TILE_SIZE + TILE_SIZE / 2;
    int pacmanCenterY = pacmanY * TILE_SIZE + TILE_SIZE / 2;

    int ghost1CenterX = ghost1X * TILE_SIZE + TILE_SIZE / 2;
    int ghost1CenterY = ghost1Y * TILE_SIZE + TILE_SIZE / 2;

    int ghost2CenterX = ghost2X * TILE_SIZE + TILE_SIZE / 2;
    int ghost2CenterY = ghost2Y * TILE_SIZE + TILE_SIZE / 2;

    int ghost3CenterX = ghost3X * TILE_SIZE + TILE_SIZE / 2;
    int ghost3CenterY = ghost3Y * TILE_SIZE + TILE_SIZE / 2;

    int ghost4CenterX = ghost4X * TILE_SIZE + TILE_SIZE / 2;
    int ghost4CenterY = ghost4Y * TILE_SIZE + TILE_SIZE / 2;

    int collisionDistance = TILE_SIZE - (TILE_SIZE / 5);
    if (Math.abs(pacmanCenterX - ghost1CenterX) < collisionDistance && Math.abs(pacmanCenterY - ghost1CenterY) < collisionDistance ||
            Math.abs(pacmanCenterX - ghost2CenterX) < collisionDistance && Math.abs(pacmanCenterY - ghost2CenterY) < collisionDistance ||
            Math.abs(pacmanCenterX - ghost3CenterX) < collisionDistance && Math.abs(pacmanCenterY - ghost3CenterY) < collisionDistance ||
            Math.abs(pacmanCenterX - ghost4CenterX) < collisionDistance && Math.abs(pacmanCenterY - ghost4CenterY) < collisionDistance) {
        lives--;
        gameFrame.updateLives(lives);
        isStarted = false;
        if (lives <= 0) {
            endGame();
        } else {
            cellPanels[pacmanY][pacmanX].remove(pacmanLabel);
            cellPanels[pacmanY][pacmanX].revalidate();
            cellPanels[pacmanY][pacmanX].repaint();
            resetPositions();
        }
    }
}
    private void resetPositions() {
        isStarted = false;

        cellPanels[pacmanY][pacmanX].remove(pacmanLabel);
        cellPanels[pacmanY][pacmanX].revalidate();
        cellPanels[pacmanY][pacmanX].repaint();

        pacmanX = startingPacmanX;
        pacmanY = startingPacmanY;
        ghost1X = startingGhost1X;
        ghost1Y = startingGhost1Y;
        ghost2X = startingGhost2X;
        ghost2Y = startingGhost2Y;
        ghost3X = startingGhost3X;
        ghost3Y = startingGhost3Y;
        ghost4X = startingGhost4X;
        ghost4Y = startingGhost4Y;
        updateCharactersPosition();
    }

    private void endGame() {
        if(!gameEnded){
        gameEnded = true;
        new GameOver(score, timerThread.getTimeOfGame(), mapName);
        gameFrame.dispose();}
    }

    private void updateCharactersPosition() {
        pacmanLabel.setBounds(pacmanX * TILE_SIZE, pacmanY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        ghost1Label.setBounds(ghost1X * TILE_SIZE, ghost1Y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        ghost2Label.setBounds(ghost2X * TILE_SIZE, ghost2Y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        ghost3Label.setBounds(ghost3X * TILE_SIZE, ghost3Y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        ghost4Label.setBounds(ghost4X * TILE_SIZE, ghost4Y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        updateCell(pacmanX, pacmanY, pacmanLabel);
    }
    private void updateCell(int x, int y, JLabel label) {
        cellPanels[y][x].add(label);
        cellPanels[y][x].revalidate();
        cellPanels[y][x].repaint();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if (opening) {
                    animationState++;
                    if (animationState == 3) {
                        opening = false;
                    }
                } else {
                    animationState--;
                    if (animationState == 1) {
                        opening = true;
                    }
                }
                pacmanLabel.setIcon(pacmanImages[pacmanDirection][animationState]);
                pacmanLabel.revalidate();
                pacmanLabel.repaint();
            }
        }
    }
}
