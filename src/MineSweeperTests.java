import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class MineSweeperTests {

    @Test
    public void testSpaceFlips() {
        GameMechanics m = new GameMechanics(true);
        Spaces s = m.getSpaces(0, 1);
        assertFalse(s.isPressed());
        s.flipSpace();
        assertTrue(s.isPressed());
    }

    @Test
    public void testMines() {
        GameMechanics m = new GameMechanics(true);
        Spaces s = m.getSpaces(0, 1);
        assertTrue(s.isMine());
        s.setAdjacent();
        assertEquals(-1, s.findTotalMines());
    }

    @Test
    public void testEmptySpaces() {
        GameMechanics m = new GameMechanics(true);
        Spaces[][] s = m.getSpace();
        Spaces empty = m.getSpaces(1, 0);
        Spaces mine1 = s[0][0];
        Spaces mine2 = s[0][1];
        Spaces mine3 = s[2][0];
        Spaces mine4 = s[2][1];
        empty.setAdjacent();
        ArrayList<Spaces> adjacent = empty.getAdjacent();
        adjacent.contains(mine1);
        adjacent.contains(mine2);
        adjacent.contains(mine3);
        adjacent.contains(mine4);
        assertEquals(4, empty.findTotalMines());
    }

    @Test
    public void testEmptySpacesEmptyAdjacent() {
        GameMechanics m = new GameMechanics(true);
        Spaces[][] s = m.getSpace();
        Spaces empty = s[7][7];
        empty.setAdjacent();
        assertEquals(0, empty.findTotalMines());
    }

    @Test
    public void testLoss() {
        GameMechanics m = new GameMechanics(true);
        m.flip(0, 0);
        assertEquals(-1,  m.gameResult());
    }

    @Test
    public void testWin() {
        GameMechanics m = new GameMechanics(true);
        m.flip(1, 0);
        assertEquals(m.getEmptySpaces(), 55);
        assertEquals(0,  m.gameResult());
        for (int row = 1; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                m.flip(row, col);
            }
        }
        assertEquals(1,  m.gameResult());
    }
}