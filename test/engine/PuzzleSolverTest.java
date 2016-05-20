package engine;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Nerijus
 */
public class PuzzleSolverTest {

    /**
     *   0
     *  |-|
     * 0| |
     *  |-|
     */
    @Test
    public void testSolveEmptyPuzzle() {
        Puzzle puzzle = Puzzle.EMPTY;
        solveAndCheck(puzzle);
    }

    /**
     *   1
     *  |-|
     * 1|x|
     *  |-|
     */
    @Test
    public void testSolvePuzzle_1x1() {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(1)),
                Arrays.asList(Question.of(1)));
        solveAndCheck(puzzle);
    }

    /**
     *   21
     *  |--|
     * 2|xx|
     * 1|x |
     *  |--|
     */
    @Test
    public void testSolvePuzzle_2x2() {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(2), Question.of(1)),
                Arrays.asList(Question.of(2), Question.of(1)));
        solveAndCheck(puzzle);
    }

    /**
     *      1
     *    311
     *   |---|
     *  3|xxx|
     *  1|x  |
     * 11|x x|
     *   |---|
     */
    @Test
    public void testSolvePuzzle_3x3() {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(3),Question.of(1),Question.of(1,1)),
                Arrays.asList(Question.of(3),Question.of(1),Question.of(1,1)));
        solveAndCheck(puzzle);
    }

    /**
     *    4241
     *   |----|
     *  3|xxx |
     *  3|xxx |
     * 12|x xx|
     * 11|X X |
     *   |----|
     */
    @Test
    public void testSolvePuzzle_4x4() {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(4),Question.of(2),Question.of(4),Question.of(1)),
                Arrays.asList(Question.of(3),Question.of(3),Question.of(1,2),Question.of(1,1)));
        solveAndCheck(puzzle);
    }

    /**
     *     313
     *    11211
     *   |-----|
     *  3| xxx |
     * 22|xx xx|
     *  3| xxx |
     *  1|  x  |
     * 11| x x |
     *   |-----|
     */
    @Test
    public void testSolvePuzzle_5x5() {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(Question.of(1),Question.of(3,1),Question.of(1,2),Question.of(3,1),Question.of(1)),
                Arrays.asList(Question.of(3),Question.of(2,2),Question.of(3),Question.of(1),Question.of(1,1)));
        solveAndCheck(puzzle);
    }

    @Test
    public void testPicture_signal_10x10() {
        Puzzle puzzle = new Puzzle(
                Arrays.asList(
                        Question.of(1),Question.of(2),Question.of(1,1),Question.of(10),Question.of(1,1),
                        Question.of(2,5),Question.of(1),Question.of(7),Question.of(0),Question.of(9)
                ),
                Arrays.asList(
                        Question.of(7),Question.of(1,1,1,1),Question.of(3,1),Question.of(1,1,1),Question.of(1,1,1),
                        Question.of(1,1,1,1),Question.of(1,1,1,1),Question.of(1,1,1,1),Question.of(1,1,1,1),Question.of(1,1,1,1)
                ));
        solveAndCheck(puzzle);
    }

    private void solveAndCheck(Puzzle puzzle) {
        Template answer = new PuzzleSolver(puzzle).solve();
        Assert.assertTrue("Puzzle is not solved", new Inspector().isSolved(puzzle, answer));
    }

    private static class Inspector {
        public boolean isSolved(Puzzle puzzle, Template fullAnswer) {
            if (containsUnsolvedFields(puzzle, fullAnswer)) return false;
            if (!checkRows(puzzle, fullAnswer)) return false;
            if (!checkColumns(puzzle, fullAnswer)) return false;
            return true;
        }

        private boolean containsUnsolvedFields(Puzzle puzzle, Template fullAnswer) {
            for (int i = 0; i < puzzle.getHeight(); i++) {
                for (int j = 0; j < puzzle.getWidth(); j++) {
                    if (fullAnswer.getRaw()[i][j] == -1) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean checkColumns(Puzzle puzzle, Template fullAnswer) {
            for (int j = 0; j < puzzle.getColumns().size(); j++) {
                if (!isCorrect(puzzle.getColumns().get(j), makeColumn(fullAnswer, j))) {
                    return false;
                }
            }
            return true;
        }

        private boolean checkRows(Puzzle puzzle, Template fullAnswer) {
            for (int i = 0; i < puzzle.getRows().size(); i++) {
                if (!isCorrect(puzzle.getRows().get(i), fullAnswer.getRaw()[i])) {
                    return false;
                }
            }
            return true;
        }

        private byte[] makeColumn(Template fullAnswer, int columnIndex) {
            byte[] columnAnswer = new byte[fullAnswer.getRaw().length];
            for (int i = 0; i < fullAnswer.getRaw().length; i++) {
                columnAnswer[i] = fullAnswer.getRaw()[i][columnIndex];
            }
            return columnAnswer;
        }

        private boolean isCorrect(Question question, byte[] lineAnswer) {
            return question.isEqualTo(Questions.toQuestion(lineAnswer));
        }
    }
}