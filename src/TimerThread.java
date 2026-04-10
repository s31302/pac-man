import javax.swing.*;

public class TimerThread extends Thread {
    private int timeOfGame = 0;
    private GameF gameFrame;

    public TimerThread(GameF gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    timeOfGame++;
                    SwingUtilities.invokeLater(() -> {
                        gameFrame.updateTime(formatTime(timeOfGame));
                        gameFrame.updateTimeLabel();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    public int getTimeOfGame() {
        return timeOfGame;
    }
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%d:%02d", minutes, remainingSeconds);
    }
}


