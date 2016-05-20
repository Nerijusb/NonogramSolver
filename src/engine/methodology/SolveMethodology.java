package engine.methodology;

import engine.Puzzle;
import engine.Question;
import engine.Template;

/**
 * Methodology that can provide at least partial solution
 * to the puzzle
 * @author Nerijus
 */
public abstract class SolveMethodology {

    protected Puzzle puzzle;

    /**
     * Constructor with puzzle currently being solved
     * @param puzzle puzzle to solve
     */
    public SolveMethodology(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Applies specific solving method and updates template
     * @param template template to add results
     */
    public abstract void apply(Template template);

    /**
     * Helper interface to use with {@link #forRows(LineAccessor)}
     * and {@link #forColumns(LineAccessor)}
     */
    @FunctionalInterface
    public interface LineAccessor {
        void apply(int index, Question q);
    }

    /**
     * Accessing all rows
     * @param accessor accessor to perform action on row
     */
    protected void forRows(LineAccessor accessor) {
        for (int i = 0; i < puzzle.getHeight(); i++) {
            Question q = puzzle.getRows().get(i);
            accessor.apply(i, q);
        }
    }

    /**
     * Accessing all columns
     * @param accessor accessor to perform action on column
     */
    protected void forColumns(LineAccessor accessor) {
        for (int j = 0; j < puzzle.getWidth(); j++) {
            Question q = puzzle.getColumns().get(j);
            accessor.apply(j, q);
        }
    }

}
