package engine.methodology.interval;

import engine.Interval;
import engine.Puzzle;
import engine.Question;
import engine.Template;
import engine.methodology.interval.IntervalBased;

import java.util.List;

/**
 * Fill a gap with {@link Template#SOLVED_EMPTY} for a gap too small for any number in question
 *  e.g. [2]{0,-1,-1,-1,0,-1,0} should become [2]{0,-1,-1,-1,0,0,0}
 *
 * @author Nerijus
 */
public class ClosingTooSmallIntervals extends IntervalBased {
    public ClosingTooSmallIntervals(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    protected void updateRow(Template template, int index, Question q, List<Interval> intervals) {
        if (q.getNumbers().size() == 1) {
            // automatically close all shorter intervals
            closeEmptyRowIntervalsShorterThan(q.getNumbers().get(0), intervals, index, template);
        } else {
            // when more than one number, lets find the smallest one
            // if it is not 1, than some empty intervals could be closed
            // TODO: check which numbers are solved, maybe smallest number can be higher
            int smallestNumber = q.getNumbers().stream().min(Integer::compareTo).get();
            if (smallestNumber > 1) {
                closeEmptyRowIntervalsShorterThan(smallestNumber, intervals, index, template);
            }
        }
    }

    private void closeEmptyRowIntervalsShorterThan(Integer integer, List<Interval> intervals, int rowIndex, Template template) {
        for (Interval i : intervals) {
            if (i.isEmpty() && i.getLength() < integer) {
                for (int j = i.getFrom(); j <= i.getTo(); j++) {
                    template.getRaw()[rowIndex][j] = Template.SOLVED_EMPTY;
                }
            }
        }
    }

    @Override
    protected void updateColumn(Template template, int index, Question q, List<Interval> intervals) {

    }
}
