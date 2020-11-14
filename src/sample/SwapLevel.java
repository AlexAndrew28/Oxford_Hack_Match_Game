package sample;

import java.util.Random;

public class SwapLevel {

    private Tiles[][] tileGrid;
    private Tiles[] previewRow;
    private int power;
    private int points;
    private int level;
    private int goal;


    public SwapLevel(int level, int goal) {
        tileGrid = new Tiles[8][8];
        previewRow = new Tiles[tileGrid.length];
        power = 0;
        points = 0;
        this.level = level;
        this.goal = goal;
    }

    private void initRand() {
        Random r = new Random();
        Tiles[] tiles = Tiles.values();
        for (int i = 0; i < tileGrid.length; i++) {
            previewRow[i] = tiles[r.nextInt(tiles.length)];
            for (int j = 0; j < tileGrid.length; j++) {
                tileGrid[i][j] = tiles[r.nextInt(tiles.length)];
            }
        }
    }

    public Tiles[][] getTileGrid() {
        return tileGrid;
    }

    public Tiles[] getPreviewRow() {
        return previewRow;
    }

    public int getPower() {
        return power;
    }

    public int getPoints() {
        return points;
    }

    public int getLevel() {
        return level;
    }

    public int getGoal() {
        return goal;
    }

}
