package engine.print;

import engine.Puzzle;
import engine.PuzzleSolver;
import engine.Question;
import engine.Template;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzlePrinterTest {

    private class PuzzlePrinterSpy extends PuzzlePrinter {
        private List<String> printedRows = new ArrayList<>();

        public PuzzlePrinterSpy(Puzzle puzzle) {
            super(puzzle);
        }

        @Override
        protected void doPrint(String row) {
            printedRows.add(row);
        }

        public void ensureExact(String... rows) {
            for (int i = 0; i < rows.length; i++) {
                Assert.assertEquals(rows[i], printedRows.get(i));
            }
        }
    }

    @Test
    public void testPrintEmptyPuzzle() {
        PuzzlePrinterSpy spy = new PuzzlePrinterSpy(Puzzle.EMPTY);
        spy.print(PuzzleSolver.EMPTY_RESULT);
        spy.ensureExact(
                 "  0 ",
                 " |-|",
                 "0| |",
                 " |-|");
    }

    @Test
    public void testPrintPuzzle() {
        PuzzlePrinterSpy spy = new PuzzlePrinterSpy(
                new Puzzle(
                        Arrays.asList(Question.of(3), Question.of(2), Question.of(1,1), Question.of(1,2)),
                        Arrays.asList(Question.of(1,2), Question.of(2), Question.of(4), Question.of(1))));
        spy.print(new Template(4, 4, new byte[][]{
                new byte[]{1,0,1,1}, new byte[]{1,1,0,0}, new byte[]{1,1,1,1}, new byte[]{0,0,0,1}
        }));
        spy.ensureExact(
                "     11 ",
                "   3212 ",
                "  |----|",
                "12|x xx|",
                " 2|xx  |",
                " 4|xxxx|",
                " 1|   x|",
                "  |----|");
    }
}
