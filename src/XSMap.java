import javax.swing.*;

public class XSMap implements Mapp{
    private int[][] map;
    private int pacmanStartX = 5;
    private int pacmanStartY = 4;
    private int ghost1StartX = 1;
    private int ghost1StartY = 1;
    private int ghost2StartX = 7;
    private int ghost2StartY = 1;
    private int ghost3StartX = 1;
    private int ghost3StartY = 7;
    private int ghost4StartX = 7;
    private int ghost4StartY = 7;
    String mapName = "XS";

    public XSMap() {
        map = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
    }

    @Override
    public String getMapName() {
        return mapName;
    }

    @Override
    public int getGhost3StartX() {
        return ghost3StartX;
    }

    @Override
    public int getGhost3StartY() {
        return ghost3StartY;
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