package engine.methodology;

import engine.Puzzle;
import engine.Question;
import engine.Template;
import engine.print.PuzzlePrinter;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Nerijus
 */
public class FirstLastFillerTest {

    /**
     *    212
     *   |---|
     *  3|xxx|
     * 11|X X|
     *  0|   |
     *   |---|
     */
    @Test
    public void testApplyOnFirstRow() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(2),Question.of(1),Question.of(2)),
                Arrays.asList(Question.of(3),Question.of(1,1),Question.of(0))
        );
        Template template = new Template(puzzle.getWidth(), puzzle.getHeight(),
                new byte[][]{
                        new byte[]{1,1,1},
                        new byte[]{-1,-1,-1},
                        new byte[]{-1,-1,-1}
                });
        FirstLastFiller f = new FirstLastFiller(puzzle);
        f.apply(template);

        new PuzzlePrinter(puzzle).print(template);

        assertEquals(1, template.getRaw()[1][0]);
        assertEquals(0, template.getRaw()[1][1]);
        assertEquals(1, template.getRaw()[1][2]);

        assertEquals(0, template.getRaw()[2][0]);
        assertEquals(-1, template.getRaw()[2][1]);
        assertEquals(0, template.getRaw()[2][2]);
    }

    /**
     *    212
     *   |---|
     *  0|   |
     * 11|X X|
     *  3|xxx|
     *   |---|
     */
    @Test
    public void testApplyOnLastRow() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(2),Question.of(1),Question.of(2)),
                Arrays.asList(Question.of(0),Question.of(1,1),Question.of(3))
        );
        Template template = new Template(puzzle.getWidth(), puzzle.getHeight(),
                new byte[][]{
                        new byte[]{-1,-1,-1},
                        new byte[]{-1,-1,-1},
                        new byte[]{1,1,1}
                });
        FirstLastFiller f = new FirstLastFiller(puzzle);
        f.apply(template);

        new PuzzlePrinter(puzzle).print(template);

        assertEquals(0, template.getRaw()[0][0]);
        assertEquals(-1, template.getRaw()[0][1]);
        assertEquals(0, template.getRaw()[0][2]);

        assertEquals(1, template.getRaw()[1][0]);
        assertEquals(0, template.getRaw()[1][1]);
        assertEquals(1, template.getRaw()[1][2]);
    }

    /**
     *     1
     *    310
     *   |---|
     *  2|xx |
     *  1|x  |
     *  2|xx |
     *   |---|
     */
    @Test
    public void testApplyOnFirstColumn() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(3),Question.of(1,1),Question.of(0)),
                Arrays.asList(Question.of(2),Question.of(1),Question.of(2))
        );
        Template template = new Template(puzzle.getWidth(), puzzle.getHeight(),
                new byte[][]{
                        new byte[]{1,-1,-1},
                        new byte[]{1,-1,-1},
                        new byte[]{1,-1,-1}
                });
        FirstLastFiller f = new FirstLastFiller(puzzle);
        f.apply(template);

        new PuzzlePrinter(puzzle).print(template);

        assertEquals(1, template.getRaw()[0][0]);
        assertEquals(1, template.getRaw()[0][1]);
        assertEquals(0, template.getRaw()[0][2]);

        assertEquals(1, template.getRaw()[1][0]);
        assertEquals(0, template.getRaw()[1][1]);
        assertEquals(-1, template.getRaw()[1][2]);

        assertEquals(1, template.getRaw()[2][0]);
        assertEquals(1, template.getRaw()[2][1]);
        assertEquals(0, template.getRaw()[2][2]);
    }

    /**
     *     1
     *    013
     *   |---|
     *  2| xx|
     *  1|  x|
     *  2| xx|
     *   |---|
     */
    @Test
    public void testApplyOnLastColumn() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(0),Question.of(1,1),Question.of(3)),
                Arrays.asList(Question.of(2),Question.of(1),Question.of(2))
        );
        Template template = new Template(puzzle.getWidth(), puzzle.getHeight(),
                new byte[][]{
                        new byte[]{-1,-1,1},
                        new byte[]{-1,-1,1},
                        new byte[]{-1,-1,1}
                });
        FirstLastFiller f = new FirstLastFiller(puzzle);
        f.apply(template);

        new PuzzlePrinter(puzzle).print(template);

        assertEquals(0, template.getRaw()[0][0]);
        assertEquals(1, template.getRaw()[0][1]);
        assertEquals(1, template.getRaw()[0][2]);

        assertEquals(-1, template.getRaw()[1][0]);
        assertEquals(0, template.getRaw()[1][1]);
        assertEquals(1, template.getRaw()[1][2]);

        assertEquals(0, template.getRaw()[2][0]);
        assertEquals(1, template.getRaw()[2][1]);
        assertEquals(1, template.getRaw()[2][2]);
    }
}