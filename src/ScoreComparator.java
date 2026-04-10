import java.util.Comparator;

class ScoreComparator implements Comparator<ScoreSerializable> {
    @Override
    public int compare(ScoreSerializable s1, ScoreSerializable s2) {
        if (s1.getScore() > s2.getScore()) {
            return -1;
        } else if (s1.getScore() < s2.getScore()) {
            return 1;
        } else {
            if (s1.getTime() < s2.getTime()) {
                return -1;
            } else if (s1.getTime() > s2.getTime()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}