package engine.methodology.interval;

import engine.Interval;
import engine.Puzzle;
import engine.Question;
import engine.Template;

import java.util.List;

/**
 * Fill partial number in interval (similar with 3, but with some part already marked)
 *  e.g. [3]{0,1,-1,-1,0} should become [3]{0,1,1,1,0}
 *
 * @author Nerijus
 */
public class PartialUnmarkedIntervals extends IntervalBased {
    public PartialUnmarkedIntervals(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    protected void updateRow(Template template, int index, Question q, List<Interval> intervals) {

    }

    @Override
    protected void updateColumn(Template template, int index, Question q, List<Interval> intervals) {

    }
}
