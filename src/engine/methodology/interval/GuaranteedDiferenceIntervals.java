package engine.methodology.interval;

import engine.Interval;
import engine.Puzzle;
import engine.Question;
import engine.Template;
import engine.methodology.GuaranteedDifferenceFiller;
import engine.methodology.interval.IntervalBased;

import java.util.List;

/**
 * Fill with at least partial answer (if the number must be in that interval, but gap is bigger)
 *  e.g. [2]{0,-1,-1,-1,0,0,0} should become [2]{0,-1,1,-1,0,0,0}
 * Similar to {@link GuaranteedDifferenceFiller} but uses not the full line, just the interval
 *
 * @author Nerijus
 */
public class GuaranteedDiferenceIntervals extends IntervalBased {
    public GuaranteedDiferenceIntervals(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    protected void updateRow(Template template, int index, Question q, List<Interval> intervals) {

    }

    @Override
    protected void updateColumn(Template template, int index, Question q, List<Interval> intervals) {

    }
}
