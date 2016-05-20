package engine.methodology.interval;

import engine.Puzzle;
import engine.Question;
import engine.Template;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Nerijus
 */
public class ClosingTooSmallIntervalsTest {
    @Test
    public void testClosingTooSmallIntervals_forRows_singleNumberQuestion() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(0),Question.of(1),Question.of(1),Question.of(1),Question.of(0),Question.of(0),Question.of(0)),
                Arrays.asList(Question.of(3)));

        ClosingTooSmallIntervals m = new ClosingTooSmallIntervals(puzzle);
        Template t = new Template(puzzle.getWidth(), puzzle.getHeight(),
                new byte[][]{new byte[]{0,-1,-1,-1,0,-1,0}});
        m.apply(t);

        assertEquals(0, t.getRaw()[0][0]);
        assertEquals(-1, t.getRaw()[0][1]);
        assertEquals(-1, t.getRaw()[0][2]);
        assertEquals(-1, t.getRaw()[0][3]);
        assertEquals(0, t.getRaw()[0][4]);
        assertEquals(0, t.getRaw()[0][5]);// changed
        assertEquals(0, t.getRaw()[0][6]);
    }

    @Test
    public void testClosingTooSmallIntervals_forRows_twoNumberQuestion() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(
                        Question.of(0),Question.of(1),Question.of(1),
                        Question.of(1),Question.of(0),Question.of(1),
                        Question.of(1),Question.of(0),Question.of(0)),
                Arrays.asList(Question.of(3,2)));

        ClosingTooSmallIntervals m = new ClosingTooSmallIntervals(puzzle);
        Template t = new Template(puzzle.getWidth(), puzzle.getHeight(),
                new byte[][]{new byte[]{0,-1,-1,-1,0,-1,-1,0,-1}});
        m.apply(t);

        assertEquals(0, t.getRaw()[0][0]);
        assertEquals(-1, t.getRaw()[0][1]);
        assertEquals(-1, t.getRaw()[0][2]);
        assertEquals(-1, t.getRaw()[0][3]);
        assertEquals(0, t.getRaw()[0][4]);
        assertEquals(-1, t.getRaw()[0][5]);
        assertEquals(-1, t.getRaw()[0][6]);
        assertEquals(0, t.getRaw()[0][7]);
        assertEquals(0, t.getRaw()[0][8]);// changed
    }

    @Test
    public void testClosingTooSmallIntervals_forColumns() throws Exception {

    }
}