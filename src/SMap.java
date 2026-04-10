import javax.swing.*;

public class SMap implements Mapp{
    private int[][] map;
    private int pacmanStartX = 7;
    private int pacmanStartY = 7;
    private int ghost1StartX = 1;
    private int ghost1StartY = 1;
    private int ghost2StartX = 13;
    private int ghost2StartY = 1;
    private int ghost3StartX = 1;
    private int ghost3StartY = 13;
    private int ghost4StartX = 13;
    private int ghost4StartY = 13;
    String mapName = "S";

    public SMap() {
        map = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
    }

    @Override
    public String getMapName() {
        return mapName;
    }

    @Override
    public int getGhost2StartX() {
        return ghost2StartX;
    }

    @Override
    public int getGhost2StartY() {
        return ghost2StartY;
    }

    @Override
    public int getGhost4StartX() {
        return ghost4StartX;
    }

    @Override
    public int getGhost4StartY() {
        return ghost4StartY;
    }

    @Override
    public int getGhost3StartX() {
        return ghost3StartX;
    }

    @Override
    public int getGhost3StartY() {
        return ghost3StartY;
    }

    public int getGhost1StartX() {
        return ghost1StartX;
    }

    public int getGhost1StartY() {
        return ghost1StartY;
    }

    public int[][] getMap() {
        return map;
    }
    public int getPacmanStartX() {
        return pacmanStartX;
    }

    public int getPacmanStartY() {
        return pacmanStartY;
    }


}
