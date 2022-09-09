import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameMechanics {

    private Spaces[][] space;
    private int gameEnd;
    private int emptySpaces;
    private LinkedList<Spaces> flippedSpaces;

    public GameMechanics() {
        reset();
    }

    public boolean flip(int r, int c) {
        if (space[r][c].isPressed() || gameEnd == -1 || gameEnd == 1) {
            return false;
        }
        space[r][c].flipSpace();
        flippedSpaces.add(space[r][c]);
        if (space[r][c].isMine()) {
            gameEnd = -1;
        } else {
            if (space[r][c].getMineCount() == 0) {
                ArrayList<Spaces> adjacent = space[r][c].getAdjacent();
                for (Spaces s : adjacent) {
                    int x = s.getX();
                    int y = s.getY();
                    flip(x, y);
                }
            }
            emptySpaces--;
        }
        if (emptySpaces == 0) {
            gameEnd = 1;
        }
        return true;
    }

    public void reset() {
        space = new Spaces[8][8];
        emptySpaces = 0;
        space = generateMines();
        gameEnd = 0;
        flippedSpaces = new LinkedList<Spaces>();
    }

    public Spaces[][] generateMines() {
        Random r = new Random();
        int i = 0;
        while (i < 9) {
            int row = r.nextInt(8);
            int col = r.nextInt(8);
            if (space[row][col] == null) {
                Spaces s = new Spaces(row, col, space, true);
                space[row][col] = s;
                i++;
            }
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (space[row][col] == null) {
                    Spaces s = new Spaces(row, col, space, false);
                    space[row][col] = s;
                    emptySpaces++;
                }
            }
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col ++) {
                space[row][col].setAdjacent();
                space[row][col].setMineCount(space[row][col].findTotalMines());
            }
        }
        return space;
    }

    public GameMechanics(boolean bool) {
        testReset();
    }

    public void testReset() {
        space = new Spaces[8][8];
        emptySpaces = 0;
        space = testGenerateMines();
        gameEnd = 0;
        flippedSpaces = new LinkedList<Spaces>();
    }

    public Spaces[][] testGenerateMines() {
        int row = 0;
        for (int col = 0; col < 8; col++) {
            if (space[row][col] == null) {
                Spaces s = new Spaces(row, col, space, true);
                space[row][col] = s;
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (space[i][j] == null) {
                    Spaces s = new Spaces(i, j, space, false);
                    space[i][j] = s;
                    emptySpaces++;
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j ++) {
                space[i][j].setAdjacent();
                space[i][j].setMineCount(space[i][j].findTotalMines());
            }
        }
        return space;
    }

    public Spaces getSpaces(int x, int y) {
        return space[x][y];
    }

    public Spaces[][] getSpace() {
        return space;
    }

    public int gameResult() {
        return gameEnd;
    }

    public int getEmptySpaces() {
        return emptySpaces;
    }

}