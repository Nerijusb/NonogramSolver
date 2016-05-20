package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nerijus
 */
public final class Intervals {

    public static List<Interval> splitRow(Template template, int index) {
        byte[] row = template.getRaw()[index];
        return splitLine(row);
    }

    static List<Interval> splitLine(byte[] line) {
        List<Interval> intervals = new ArrayList<>();
        Interval interval = null;
        for (int i = 0; i < line.length; i++) {
            byte value = line[i];
            if (value == Template.SOLVED_EMPTY) {
                if (interval != null) {
                    fillIntervalInfo(interval, line, i - 1);
                    intervals.add(interval);
                    interval = null;
                }
            } else if (value == Template.SOLVED_CHECK || value == Template.UNSOLVED) {
                if (interval == null) {
                    interval = new Interval();
                    interval.from = i;
                }
            }
        }
        if (interval != null) {
            fillIntervalInfo(interval, line, line.length - 1);
            intervals.add(interval);
        }
        return intervals;
    }

    private static void fillIntervalInfo(Interval interval, byte[] line, int indexTo) {
        assert interval.from != -1;
        interval.to = indexTo;
        interval.length = interval.to - interval.from + 1;
        interval.values = Arrays.copyOfRange(line, interval.from, interval.to + 1);
        interval.display = Templates.display(interval.values);

        interval.solved = !interval.display.contains(Templates.UNSOLVED_DISPLAY);
        interval.unsolved = !interval.solved;
        interval.partiallySolved = interval.display.contains(Templates.UNSOLVED_DISPLAY) && interval.display.contains(Templates.SOLVED_CHECK_DISPLAY);
        interval.empty = !interval.display.contains(Templates.SOLVED_CHECK_DISPLAY);
    }
}
