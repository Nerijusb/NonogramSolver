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
public class GuaranteedDifferenceFillerTest {

    @Test
    public void testRowApply() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(1),Question.of(1),Question.of(1),Question.of(1)),
                Arrays.asList(Question.of(3))
        );
        Template template = new Template(puzzle.getWidth(), puzzle.getHeight());
        GuaranteedDifferenceFiller f = new GuaranteedDifferenceFiller(puzzle);
        f.apply(template);

        new PuzzlePrinter(puzzle).print(template);

        assertEquals(-1, template.getRaw()[0][0]);
        assertEquals(1, template.getRaw()[0][1]);
        assertEquals(1, template.getRaw()[0][2]);
        assertEquals(-1, template.getRaw()[0][3]);
    }

    @Test
    public void testColumnApply() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(3)),
                Arrays.asList(Question.of(1),Question.of(1),Question.of(1),Question.of(1))
        );
        Template template = new Template(puzzle.getWidth(), puzzle.getHeight());
        GuaranteedDifferenceFiller f = new GuaranteedDifferenceFiller(puzzle);
        f.apply(template);

        new PuzzlePrinter(puzzle).print(template);

        assertEquals(-1, template.getRaw()[0][0]);
        assertEquals(1, template.getRaw()[1][0]);
        assertEquals(1, template.getRaw()[2][0]);
        assertEquals(-1, template.getRaw()[3][0]);
    }

    @Test
    public void testApply_noChangesIfNotApplicable() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(0),Question.of(1),Question.of(0),Question.of(1),Question.of(0),
                        Question.of(1),Question.of(0),Question.of(1),Question.of(0),Question.of(0)),
                Arrays.asList(Question.of(1,1,1,1))
        );
        Template template = new Template(puzzle.getWidth(), puzzle.getHeight());
        GuaranteedDifferenceFiller f = new GuaranteedDifferenceFiller(puzzle);
        f.apply(template);

        new PuzzlePrinter(puzzle).print(template);

        assertEquals(-1, template.getRaw()[0][0]);
        assertEquals(-1, template.getRaw()[0][1]);
        assertEquals(-1, template.getRaw()[0][2]);
        assertEquals(-1, template.getRaw()[0][3]);
        assertEquals(-1, template.getRaw()[0][4]);
        assertEquals(-1, template.getRaw()[0][5]);
        assertEquals(-1, template.getRaw()[0][6]);
        assertEquals(-1, template.getRaw()[0][7]);
        assertEquals(-1, template.getRaw()[0][8]);
        assertEquals(-1, template.getRaw()[0][9]);
    }

    @Test
    public void debug() throws Exception {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(5,2)),
                Arrays.asList(Question.of(0),Question.of(0),Question.of(1),Question.of(1),Question.of(1),Question.of(1),
                        Question.of(1),Question.of(0),Question.of(1),Question.of(1))
        );
        Template template = new Template(puzzle.getWidth(), puzzle.getHeight());
        GuaranteedDifferenceFiller f = new GuaranteedDifferenceFiller(puzzle);
        f.apply(template);

        new PuzzlePrinter(puzzle).print(template);

        /*assertEquals(-1, template.getRaw()[0][0]);
        assertEquals(1, template.getRaw()[1][0]);
        assertEquals(1, template.getRaw()[2][0]);
        assertEquals(-1, template.getRaw()[3][0]);*/
    }
}