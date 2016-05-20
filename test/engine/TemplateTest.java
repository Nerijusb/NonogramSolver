package engine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nerijus
 */
public class TemplateTest {

    private Template unsolved;
    private Template solved;
    private Template mixed;

    @Before
    public void setUp() {
        unsolved = new Template(3, 5);
        solved = new Template(3, 5, new byte[][]{
                new byte[] {0,0,0},
                new byte[] {1,1,1},
                new byte[] {1,1,0},
                new byte[] {1,0,0},
                new byte[] {0,0,1}
        });
        mixed = new Template(3, 5, new byte[][]{
                new byte[] {0,-1,0},
                new byte[] {1,1,1},
                new byte[] {-1,-1,-1},
                new byte[] {1,0,0},
                new byte[] {0,-1,-1}
        });
    }

    @Test
    public void testTemplateDimensions() throws Exception {
        assertEquals(3, unsolved.getWidth());
        assertEquals(5, unsolved.getHeight());
        assertEquals(3, solved.getWidth());
        assertEquals(5, solved.getHeight());
        assertEquals(3, mixed.getWidth());
        assertEquals(5, mixed.getHeight());
    }

    @Test
    public void testGetRaw() {
        assertTrue(unsolved.getRaw() == unsolved.getRaw());
        assertTrue(solved.getRaw() == solved.getRaw());
        assertTrue(mixed.getRaw() == mixed.getRaw());
    }

    @Test
    public void testCountUnsolved() throws Exception {
        assertEquals(15, unsolved.countUnsolved());
        assertEquals(0, solved.countUnsolved());
        assertEquals(6, mixed.countUnsolved());
    }

    @Test
    public void testCountSolved() throws Exception {
        assertEquals(0, unsolved.countSolved());
        assertEquals(15, solved.countSolved());
        assertEquals(9, mixed.countSolved());
    }

    @Test
    public void testIsSolved() throws Exception {
        assertEquals(false, unsolved.isSolved());
        assertEquals(true, solved.isSolved());
        assertEquals(false, mixed.isSolved());
    }

    @Test
    public void testCountUnsolvedInRow() throws Exception {
        assertEquals(3, unsolved.countUnsolvedInRow(0));
        assertEquals(3, unsolved.countUnsolvedInRow(1));
        assertEquals(3, unsolved.countUnsolvedInRow(2));
        assertEquals(3, unsolved.countUnsolvedInRow(3));
        assertEquals(3, unsolved.countUnsolvedInRow(4));
        assertEquals(0, solved.countUnsolvedInRow(0));
        assertEquals(0, solved.countUnsolvedInRow(1));
        assertEquals(0, solved.countUnsolvedInRow(2));
        assertEquals(0, solved.countUnsolvedInRow(3));
        assertEquals(0, solved.countUnsolvedInRow(4));
        assertEquals(1, mixed.countUnsolvedInRow(0));
        assertEquals(0, mixed.countUnsolvedInRow(1));
        assertEquals(3, mixed.countUnsolvedInRow(2));
        assertEquals(0, mixed.countUnsolvedInRow(3));
        assertEquals(2, mixed.countUnsolvedInRow(4));
    }

    @Test
    public void testCountCheckedInRow() throws Exception {
        assertEquals(0, unsolved.countCheckedInRow(0));
        assertEquals(0, unsolved.countCheckedInRow(1));
        assertEquals(0, unsolved.countCheckedInRow(2));
        assertEquals(0, unsolved.countCheckedInRow(3));
        assertEquals(0, unsolved.countCheckedInRow(4));
        assertEquals(0, solved.countCheckedInRow(0));
        assertEquals(3, solved.countCheckedInRow(1));
        assertEquals(2, solved.countCheckedInRow(2));
        assertEquals(1, solved.countCheckedInRow(3));
        assertEquals(1, solved.countCheckedInRow(4));
        assertEquals(0, mixed.countCheckedInRow(0));
        assertEquals(3, mixed.countCheckedInRow(1));
        assertEquals(0, mixed.countCheckedInRow(2));
        assertEquals(1, mixed.countCheckedInRow(3));
        assertEquals(0, mixed.countCheckedInRow(4));
    }

    @Test
    public void testCountUnsolvedInColumn() throws Exception {
        assertEquals(5, unsolved.countUnsolvedInColumn(0));
        assertEquals(5, unsolved.countUnsolvedInColumn(1));
        assertEquals(5, unsolved.countUnsolvedInColumn(2));
        assertEquals(0, solved.countUnsolvedInColumn(0));
        assertEquals(0, solved.countUnsolvedInColumn(1));
        assertEquals(0, solved.countUnsolvedInColumn(2));
        assertEquals(1, mixed.countUnsolvedInColumn(0));
        assertEquals(3, mixed.countUnsolvedInColumn(1));
        assertEquals(2, mixed.countUnsolvedInColumn(2));
    }

    @Test
    public void testCountCheckedInColumn() throws Exception {
        assertEquals(0, unsolved.countCheckedInColumn(0));
        assertEquals(0, unsolved.countCheckedInColumn(1));
        assertEquals(0, unsolved.countCheckedInColumn(2));
        assertEquals(3, solved.countCheckedInColumn(0));
        assertEquals(2, solved.countCheckedInColumn(1));
        assertEquals(2, solved.countCheckedInColumn(2));
        assertEquals(2, mixed.countCheckedInColumn(0));
        assertEquals(1, mixed.countCheckedInColumn(1));
        assertEquals(1, mixed.countCheckedInColumn(2));
    }
}