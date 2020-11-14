package sample;

import java.util.ArrayList;
import java.util.Random;

public class SwapLevel {

    private Tiles[][] tileGrid;
    private Tiles[] previewRow;
    private int power;
    private int acceleration;
    private int points;
    private int level;
    private int goal;
    private Tiles[] randTable;


    public SwapLevel(int level, int goal) {
        tileGrid = new Tiles[8][8];
        previewRow = new Tiles[tileGrid.length];
        power = 0;
        points = goal/3;
        acceleration = 0;
        this.level = level;
        this.goal = goal;
        genRandArray();
        initialize();
    }

    private void genRandArray() {
        Tiles[] tiles = Tiles.values();
        ArrayList<Tiles> randArray = new ArrayList<Tiles>();
        for (Tiles tile : tiles) {
            for (int i = 0; i < tile.getWeight(); i++) {
                randArray.add(tile);
                System.out.println(tile.toString());
            }
        }
        randTable = new Tiles[randArray.size()];
        for (int i = 0; i < randArray.size(); i++) {
            randTable[i] = randArray.get(i);
        }
    }

    public void simulate() {

    }

    public void elimMatches() {
        for (int i = 0; i < 8; i++) {
            int lastSamex = 0;
            Tiles lastSame = null;
            for (int j = 0; j < 8; j++) {
                if (tileGrid[j][i] != lastSame) {
                    if (j - lastSamex > 2) {

                    }
                    lastSame = tileGrid[j][i];
                    lastSamex = j;
                }
            }
        }
    }

    public void initRand() {
        Random r = new Random();
        Tiles[] tiles = Tiles.values();
        for (int i = 0; i < tileGrid.length; i++) {
            previewRow[i] = tiles[r.nextInt(tiles.length)];
            for (int j = 0; j < tileGrid.length; j++) {
                tileGrid[i][j] = tiles[r.nextInt(tiles.length)];
            }
        }
    }

    public void initialize() {
        Random r = new Random();
        Tiles[] tiles = Tiles.values();
        for (int i = 0; i < tileGrid.length; i++) {
            previewRow[i] = randTable[r.nextInt(randTable.length)];
            for (int j = 0; j < tileGrid.length; j++) {
                tileGrid[i][j] = randTable[r.nextInt(randTable.length)];
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

    public int getAcceleration() {
        return acceleration;
    }

}
