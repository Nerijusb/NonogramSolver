package engine.methodology.interval;

import engine.Interval;
import engine.Puzzle;
import engine.Question;
import engine.Template;
import engine.methodology.GuaranteedDifferenceFiller;

import java.util.List;

/**
 * Fill full interval when number which must be in that interval and size matches the number
 *  e.g. [3]{0,-1,-1,-1,0} should become [3]{0,1,1,1,0}
 *
 * @author Nerijus
 */
public class FullUnmarkedIntervals extends IntervalBased {
    public FullUnmarkedIntervals(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    protected void updateRow(Template template, int index, Question q, List<Interval> intervals) {

    }

    @Override
    protected void updateColumn(Template template, int index, Question q, List<Interval> intervals) {

    }
}
