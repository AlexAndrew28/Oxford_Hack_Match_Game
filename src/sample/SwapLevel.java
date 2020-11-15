package sample;

import java.util.ArrayList;
import java.util.Random;

public class SwapLevel {

    private Tiles[][] tileGrid;
    private Tiles[][] oldGrid;
    private Tiles[] previewRow;
    private int power;
    private int acceleration;
    private int points;
    private int level;
    private int goal;
    private int moves;
    private Tiles[] randTable;
    private boolean running;


    public SwapLevel(int level, int goal, int moves) {
        tileGrid = new Tiles[8][8];
        oldGrid = new Tiles[8][8];
        previewRow = new Tiles[tileGrid.length];
        power = 0;
        points = 0;
        acceleration = 0;
        this.level = level;
        this.goal = goal;
        running = false;
        this.moves = moves;
        genRandArray();
        initialize();
        copyGrid();
        running = true;
//        printGrid();
    }

    private void copyGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                oldGrid[i][j] = tileGrid[i][j];
            }
        }
    }

    private void revert() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tileGrid[i][j] = oldGrid[i][j];
            }
        }
    }

    private void printGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(tileGrid[i][j].toString());
            }
        }
    }

    private void genRandArray() {
        Tiles[] tiles = Tiles.values();
        ArrayList<Tiles> randArray = new ArrayList<Tiles>();
        for (Tiles tile : tiles) {
            for (int i = 0; i < tile.getWeight(); i++) {
                randArray.add(tile);
//                System.out.println(tile.toString());
            }
        }
        randTable = new Tiles[randArray.size()];
        for (int i = 0; i < randArray.size(); i++) {
            randTable[i] = randArray.get(i);
        }
    }

    public void simulate() {
        boolean changed = false;
        while (elimMatches()) {
            changed = true;
            collapse();
            refill();
        }
        if (!changed)
            revert();
        else {
            if (running)
                moves--;
        }

        copyGrid();
    }

    public void refill() {
        boolean filled = false;
        while (!filled) {
            filled = true;
            dropRow();
            for (Tiles tile : previewRow) {
                if (tile == Tiles.blank)
                    filled = false;
            }
            fillPreview();
        }
    }

    private void fillPreview() {
        Random r = new Random();
        for (int i = 0; i < tileGrid.length; i++) {
            if (previewRow[i] == Tiles.blank || previewRow[i] == null)
                previewRow[i] = randTable[r.nextInt(randTable.length)];
        }
    }

    public void dropRow() {
        for (int i = 0; i < 8; i++) {
            int j = -1;
            while (j < 7 && tileGrid[i][j+1] == Tiles.blank) {
                j++;
            }
            if (j >= 0) {
                tileGrid[i][j] = previewRow[i];
                previewRow[i] = Tiles.blank;
            }
        }
    }

    public void collapse() {
        for (int i = 0; i < 8; i++) {
            for (int j = 7; j >= 0; j--) {
                tileGrid[i][j] = getLowTile(i,j);
            }
        }
    }

    public Tiles getLowTile(int x, int y) {
        if (y < 0)
            return Tiles.blank;
        else if (tileGrid[x][y] != Tiles.blank) {
            Tiles tile = tileGrid[x][y];
            tileGrid[x][y] = Tiles.blank;
            return tile;
        } else
            return getLowTile(x, y-1);
    }

    private void score(ArrayList<Tiles> match) {
        if (running) {
            for (Tiles tile : match)
                System.out.println(tile.toString());
            System.out.println();
            int score = 0;
            if (Tiles.basic(match.get(0)) == Tiles.orange || Tiles.basic(match.get(1)) == Tiles.cyan || Tiles.basic(match.get(1)) == Tiles.red || Tiles.basic(match.get(1)) == Tiles.green) {
                score = match.size() * GameStats.baseValue;
                if (match.size() - 3 > 0)
                    score *= Math.pow(GameStats.bigMatchMult, match.size() - 2);
                for (Tiles tile : match) {
                    if (Tiles.basic(tile) != tile)
                        score *= GameStats.superMult;
                }
                points += score * (1 + acceleration / 100.0);
            } else {
                switch (match.get(0)) {
                    case power:
                        power += match.size() * GameStats.powerBaseVal * Math.pow(GameStats.bigMatchMult, Math.max(0, match.size() - 3));
                        break;
                    case accelerate:
                        acceleration += match.size() * GameStats.accBaseVal * Math.pow(GameStats.bigMatchMult, Math.max(0, match.size() - 3));
                        break;
                    case decelerate:
                        points -= match.size() * GameStats.accBaseVal * Math.pow(GameStats.bigMatchMult, Math.max(0, match.size() - 3)) * 3;
                        acceleration -= match.size() * GameStats.accBaseVal * Math.pow(GameStats.bigMatchMult, Math.max(0, match.size() - 3)) * 2;
                        break;
                    case extraMove:
                        moves += Math.pow(2, match.size() - 3);
                        break;
                }
                acceleration = Math.max(acceleration, -50);
            }
        }
    }

    public boolean elimMatches() {
        Random r = new Random();
        boolean changed = false;
        for (int i = 0; i < 8; i++) {
            int lastSamex = 0;
            Tiles lastSame = null;
            for (int j = 0; j < 8; j++) {
                if (Tiles.basic(tileGrid[j][i]) != lastSame) {
                    if (j - lastSamex > 2) {
                        ArrayList<Tiles> match = new ArrayList<Tiles>();
                        changed = true;
                        for (int k = lastSamex; k < j; k++) {
                            match.add(tileGrid[k][i]);
                            tileGrid[k][i] = Tiles.blank;
                        }
                        score(match);
                        if (j - lastSamex > 4) {
                            if (r.nextDouble() < GameStats.match5super && lastSame.getMulti() != null) {
                                tileGrid[lastSamex+2][i] = lastSame.getMulti();
                            }
                        } else if (j - lastSamex > 3) {
                            if (r.nextDouble() < GameStats.match4super && lastSame.getMulti() != null) {
                                tileGrid[lastSamex+2][i] = lastSame.getMulti();
                            }
                        }
                    }
                    lastSame = Tiles.basic(tileGrid[j][i]);
                    lastSamex = j;
                }
            }
            if (8 - lastSamex > 2) {
                changed = true;
                ArrayList<Tiles> match = new ArrayList<Tiles>();
                for (int k = lastSamex; k < 8; k++) {
                    match.add(tileGrid[k][i]);
                    tileGrid[k][i] = Tiles.blank;
                }
                score(match);
                if (8 - lastSamex > 4) {
                    if (r.nextDouble() < GameStats.match5super && lastSame.getMulti() != null) {
                        tileGrid[lastSamex+2][i] = lastSame.getMulti();
                    }
                } else if (8 - lastSamex > 3) {
                    if (r.nextDouble() < GameStats.match4super && lastSame.getMulti() != null) {
                        tileGrid[lastSamex+2][i] = lastSame.getMulti();
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            int lastSamey = 0;
            Tiles lastSame = null;
            for (int j = 0; j < 8; j++) {
                if (Tiles.basic(tileGrid[i][j]) != lastSame) {
                    if (j - lastSamey > 2) {
                        ArrayList<Tiles> match = new ArrayList<Tiles>();
                        changed = true;
                        for (int k = lastSamey; k < j; k++) {
                            match.add(tileGrid[i][k]);
                            tileGrid[i][k] = Tiles.blank;
                        }
                        score(match);
                        if (j - lastSamey > 4) {
                            if (r.nextDouble() < GameStats.match5super && lastSame.getMulti() != null) {
                                tileGrid[i][lastSamey+2] = lastSame.getMulti();
                            }
                        } else if (j - lastSamey > 3) {
                            if (r.nextDouble() < GameStats.match4super && lastSame.getMulti() != null) {
                                tileGrid[i][lastSamey+2] = lastSame.getMulti();
                            }
                        }
                    }
                    lastSame = Tiles.basic(tileGrid[i][j]);
                    lastSamey = j;
                }
            }
            if (8 - lastSamey > 2) {
                ArrayList<Tiles> match = new ArrayList<Tiles>();
                changed = true;
                for (int k = lastSamey; k < 8; k++) {
                    match.add(tileGrid[i][k]);
                    tileGrid[i][k] = Tiles.blank;
                }
                score(match);
                if (8 - lastSamey > 4) {
                    if (r.nextDouble() < GameStats.match5super && lastSame.getMulti() != null) {
                        tileGrid[i][lastSamey+2] = lastSame.getMulti();
                    }
                } else if (8 - lastSamey > 3) {
                    if (r.nextDouble() < GameStats.match4super && lastSame.getMulti() != null) {
                        tileGrid[i][lastSamey+2] = lastSame.getMulti();
                    }
                }
            }
        }
        return changed;
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
        printGrid();
        simulate();
        printGrid();
    }

    public Tiles[][] getTileGrid() {
        return tileGrid;
    }

    public Tiles[] getPreviewRow() {
        return previewRow;
    }

    public int getMoves() {
        return moves;
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