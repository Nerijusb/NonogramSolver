package engine.methodology.interval;

import engine.*;
import engine.methodology.GuaranteedDifferenceFiller;
import engine.methodology.SolveMethodology;

import java.util.List;

/**
 * Base class for methodologies that uses interval analysis
 * (intervals separated by single or multiple {@link Template#SOLVED_EMPTY})
 *
 * @author Nerijus
 */
public abstract class IntervalBased extends SolveMethodology {

    public IntervalBased(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    public final void apply(Template template) {
        forRows((index, q) -> updateRow(template, index, q));
        forColumns((index, q) -> updateColumn(template, index, q));
    }

    private void updateRow(Template template, int index, Question q) {
        if (!isRowApplicable(template, index)) {
            return;
        }
        updateRow(template, index, q, Intervals.splitRow(template, index));
    }

    protected abstract void updateRow(Template template, int index, Question q, List<Interval> intervals);

    private void updateColumn(Template template, int index, Question q) {
        if (!isColumnApplicable(template, index)) {
            return;
        }
        //updateColumn(template, index, q, Intervals.splitColumn(template, index));
    }

    protected abstract void updateColumn(Template template, int index, Question q, List<Interval> intervals);

    protected boolean isRowApplicable(Template template, int index) {
        return !template.isRowSolved(index) && template.countSolvedEmptyInRow(index) > 0;
    }

    protected boolean isColumnApplicable(Template template, int index) {
        return template.isColumnSolved(index) && template.countSolvedEmptyInColumn(index) > 0;
    }
}
