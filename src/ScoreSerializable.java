import java.io.Serializable;

public class ScoreSerializable implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nick;
    private int score;
    private float time;
    private String mapName;

    public ScoreSerializable(String nick, int score, float time, String mapName) {
        this.nick = nick;
        this.score = score;
        this.time = time;
        this.mapName = mapName;
    }

    public String getNick() {
        return nick;
    }

    public int getScore() {
        return score;
    }

    public float getTime() {
        return time;
    }

    public String getMapName() {
        return mapName;
    }

    @Override
    public String toString() {
        int minutes = (int) (time / 60);
        int seconds = (int) (time % 60);
        String timeString = String.format("%02d:%02d", minutes, seconds);
        return "Nick: " + nick + " ___ Score: " + score + " ___ Time: " + timeString;
    }
}