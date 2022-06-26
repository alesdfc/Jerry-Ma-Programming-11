import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    // Tests for the leftClick function on a mine
    @Test
    public void testClicking() {
        board.leftClick(0, 0);
        assertEquals(board.getHasLost(), true);
    }

    // Tests for the leftCLick function on tiles and winning
    @Test
    public void testWinning() {
        board.leftClick(0, 1);
        assertEquals(board.getHasWon(), false);
        board.leftClick(1, 0);
        assertTrue(board.getHasWon());
    }

    // Tests rightClick function and flags remaining
    @Test
    public void testFlags() {
        board.rightClick(0, 1);
        assertEquals(board.getFlagsLeft(), 1);
        board.rightClick(0, 0);
        assertEquals(board.getFlagsLeft(), 0);
    }

    // Tests the chording function via left clicking
    @Test
    public void testChording() {
        board.rightClick(0, 0);
        board.rightClick(1, 1);
        board.leftClick(0, 1);
        board.leftClick(0, 1);
        assertTrue(board.getHasWon());
    }
}
