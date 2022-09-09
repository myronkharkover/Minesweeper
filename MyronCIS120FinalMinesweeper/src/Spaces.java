import java.util.ArrayList;
public class Spaces {

    private int x;
    private int y;
    private int mineCount;
    private boolean mine;
    private boolean pressed;
    private Spaces[][] s;
    private ArrayList<Spaces> adjacent;

    public Spaces(int xpos, int ypos, Spaces[][] space, boolean isMine) {
        this.mineCount = -1;
        this.x = xpos;
        this.y = ypos;
        this.s = space;
        this.mine = isMine;
        this.pressed = false;
        this.adjacent = new ArrayList<Spaces>();
    }

    public void setAdjacent() {
        if (!this.isMine()) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int x1 = i + this.x;
                    int y1 = j + this.y;
                    if (!((x1 == this.x) && (y1 == this.y))) {
                        int w = s[0].length;
                        int h = s.length;
                        if (y1 >= 0 && y1 < h && x1 >= 0 && x1 < w) {
                            adjacent.add(s[x1][y1]);
                        }
                    }
                }
            }
        }
    }

    public int findTotalMines() {
        if (!this.isMine()) {
            int i = 0;
            for (Spaces s : adjacent) {
                if (s.isMine()) {
                    i++;
                }
            }
            return i;
        }
        return -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void flipSpace() {
        this.pressed = true;
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int m) {
        mineCount = m;
    }

    public ArrayList<Spaces> getAdjacent() {
        return adjacent;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isMine() {
        return mine;
    }

}