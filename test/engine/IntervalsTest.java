package engine;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Nerijus
 */
public class IntervalsTest {
    @Test
    public void testSplitEmptySolvedLine() {
        List<Interval> intervals = Intervals.splitLine(new byte[] {0,0,0,0,0});
        assertEquals(0, intervals.size());
    }

    @Test
    public void testSplitEmptyUnsolvedLine() {
        List<Interval> intervals = Intervals.splitLine(new byte[] {-1,-1,-1,-1,-1});
        assertEquals(1, intervals.size());
        assertEquals(0, intervals.get(0).getFrom());
        assertEquals(4, intervals.get(0).getTo());
        assertEquals(5, intervals.get(0).getLength());
        assertEquals("?????", intervals.get(0).getDisplay());
    }

    @Test
    public void testSplitFullSolvedRow() {
        List<Interval> intervals = Intervals.splitLine(new byte[] {1,1,1,1,1});
        assertEquals(1, intervals.size());
        assertEquals(0, intervals.get(0).getFrom());
        assertEquals(4, intervals.get(0).getTo());
        assertEquals(5, intervals.get(0).getLength());
        assertEquals("xxxxx", intervals.get(0).getDisplay());
    }

    @Test
    public void testSplitMixedRow_singleInterval() {
        List<Interval> intervals = Intervals.splitLine(new byte[] {0,-1,-1,1,0});
        assertEquals(1, intervals.size());

        assertEquals(1, intervals.get(0).getFrom());
        assertEquals(3, intervals.get(0).getTo());
        assertEquals(3, intervals.get(0).getLength());
        assertEquals("??x", intervals.get(0).getDisplay());
    }

    @Test
    public void testSplitMixedRow_twoIntervals() {
        List<Interval> intervals = Intervals.splitLine(new byte[] {-1,-1,0,1,-1,0});
        assertEquals(2, intervals.size());

        assertEquals(0, intervals.get(0).getFrom());
        assertEquals(1, intervals.get(0).getTo());
        assertEquals(2, intervals.get(0).getLength());
        assertEquals("??", intervals.get(0).getDisplay());

        assertEquals(3, intervals.get(1).getFrom());
        assertEquals(4, intervals.get(1).getTo());
        assertEquals(2, intervals.get(1).getLength());
        assertEquals("x?", intervals.get(1).getDisplay());
    }

    @Test
    public void testSplitMixedRow_threeIntervals() {
        List<Interval> intervals = Intervals.splitLine(new byte[] {0,-1,-1,0,1,-1,1,0,0,0,1,1,1});
        assertEquals(3, intervals.size());

        assertEquals(1, intervals.get(0).getFrom());
        assertEquals(2, intervals.get(0).getTo());
        assertEquals(2, intervals.get(0).getLength());
        assertEquals("??", intervals.get(0).getDisplay());

        assertEquals(4, intervals.get(1).getFrom());
        assertEquals(6, intervals.get(1).getTo());
        assertEquals(3, intervals.get(1).getLength());
        assertEquals("x?x", intervals.get(1).getDisplay());

        assertEquals(10, intervals.get(2).getFrom());
        assertEquals(12, intervals.get(2).getTo());
        assertEquals(3, intervals.get(2).getLength());
        assertEquals("xxx", intervals.get(2).getDisplay());
    }
}