package engine;

import engine.methodology.*;
import engine.print.PuzzlePrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * Puzzle solver
 * @author Nerijus
 */
public class PuzzleSolver {
    public static final Template EMPTY_RESULT = new Template(1, 1, new byte[][]{new byte[]{0}});

    private Puzzle puzzle;

    private List<SolveMethodology> methods = new ArrayList<>();
    private Template template;

    public PuzzleSolver(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.template = new Template(puzzle.getWidth(), puzzle.getHeight());
        initMethods();
    }

    private void initMethods() {
        methods.add(new SingleAnswerLineFiller(puzzle));
        methods.add(new SolvedEmptyFiller(puzzle));
        methods.add(new GuaranteedDifferenceFiller(puzzle));
        methods.add(new SinglesOnlyFiller(puzzle));
        methods.add(new FirstLastFiller(puzzle));
    }

    /**
     * Solves the puzzle
     * @return result as a {@link Template}
     */
    public Template solve() {
        boolean progress = true;
        while (progress) {
            progress = applyMethods();
        }
        new PuzzlePrinter(puzzle).print(template);
        //if (template.isSolved()) {
            return template;
        //} else {
        //    throw new PuzzleUnsolvedException();
        //}
    }

    /**
     * Applies methodologies to template
     * @return true if any cell was solved
     */
    private boolean applyMethods() {
        int initialUnsolvedCount = template.countUnsolved();
        for (SolveMethodology m : methods) {
            m.apply(template);
        }
        int newUnsolvedCount = template.countUnsolved();
        return newUnsolvedCount < initialUnsolvedCount;
    }
}
