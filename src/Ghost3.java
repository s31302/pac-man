import java.util.Random;

public class Ghost3 implements Runnable {
    private final GameP gameP;
    private int ghostX;
    private int ghostY;
    private int ghostDirection;
    private int animationState = 1;
    private boolean frozen = false;

    private static final int GHOST_MOVEMENT_DELAY = 300;
    private static final int GHOST_ANIMATION_DELAY = 160;

    public Ghost3(GameP gameP, int initialX, int initialY) {
        this.gameP = gameP;
        this.ghostX = initialX;
        this.ghostY = initialY;
        this.ghostDirection = 1;

    }
    @Override
    public void run() {
        Thread animationTread = new Thread(this::animate);
        animationTread.start();

        while (true) {
            try {
                Thread.sleep(GHOST_MOVEMENT_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
        }
    }
    private void animate() {
        while (true) {
            try {
                Thread.sleep(GHOST_ANIMATION_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            animationState++;
            if (animationState > 2) {
                animationState = 1;
            }
            gameP.updateGhost3Image(ghostX, ghostY, ghostDirection, animationState);
        }
    }

    private void move() {
        int newMoveX = ghostX;
        int newMoveY = ghostY;
        int direction = (int) (Math.random() * 4 ) + 1;

        switch (direction) {
            case 4:
                ghostDirection = 2;
                newMoveY++;
                break;
            case 3:
                ghostDirection = 4;
                newMoveY--;
                break;
            case 1:
                ghostDirection = 1;
                newMoveX++;
                break;
            case 2:
                ghostDirection = 3;
                newMoveX--;
                break;
        }
        if (gameP.isValidMove(newMoveX, newMoveY)) {
            gameP.moveGhost3(ghostX, ghostY, newMoveX, newMoveY);
            ghostX = newMoveX;
            ghostY = newMoveY;
        }
    }
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}