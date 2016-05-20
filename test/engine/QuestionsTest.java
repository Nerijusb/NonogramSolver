package engine;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Nerijus
 */
public class QuestionsTest {
    @Test
    public void testQuestionsMaxLengthOf() {
        assertEquals(1, Questions.maxLengthOf(Arrays.asList(Question.EMPTY)));
        assertEquals(2, Questions.maxLengthOf(Arrays.asList(Question.of(1), Question.of(1,1))));
        assertEquals(3, Questions.maxLengthOf(Arrays.asList(Question.of(1,2,3), Question.of(1))));
    }

    @Test
    public void testQuestionsToString() throws Exception {
        assertEquals("0", Questions.toString(Question.EMPTY, 0));
        assertEquals("158", Questions.toString(Question.of(1,5,8), 0));
        assertEquals("   158", Questions.toString(Question.of(1,5,8), 3));
    }

    @Test
    public void testQuestionsToQuestion_withEmptyRow() throws Exception {
        byte[] a = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Question q = Questions.toQuestion(a);
        assertEquals(1, q.getLength());
        assertTrue(0 == q.getNumbers().get(0));
    }

    @Test
    public void testQuestionsToQuestion_withFullRow() throws Exception {
        byte[] a = new byte[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        Question q = Questions.toQuestion(a);
        assertEquals(1, q.getLength());
        assertTrue(9 == q.getNumbers().get(0));
    }

    @Test
    public void testQuestionsToQuestion_withMixedRow() throws Exception {
        byte[] a1 = new byte[]{1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1};
        Question q1 = Questions.toQuestion(a1);
        assertEquals(3, q1.getLength());
        assertTrue(1 == q1.getNumbers().get(0));
        assertTrue(5 == q1.getNumbers().get(1));
        assertTrue(2 == q1.getNumbers().get(2));

        byte[] a2 = new byte[]{0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0};
        Question q2 = Questions.toQuestion(a2);
        assertEquals(3, q2.getLength());
        assertTrue(1 == q2.getNumbers().get(0));
        assertTrue(3 == q2.getNumbers().get(1));
        assertTrue(1 == q2.getNumbers().get(2));

        byte[] a3 = new byte[]{1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0};
        Question q3 = Questions.toQuestion(a3);
        assertEquals(4, q3.getLength());
        assertTrue(2 == q3.getNumbers().get(0));
        assertTrue(1 == q3.getNumbers().get(1));
        assertTrue(3 == q3.getNumbers().get(2));
        assertTrue(1 == q3.getNumbers().get(3));
    }

    @Test
    public void testToSimplestAnswer() throws Exception {
        byte[] a1 = Questions.toSimplestAnswer(Question.EMPTY);
        assertEquals(1, a1.length);
        assertEquals(0, a1[0]);

        byte[] a2 = Questions.toSimplestAnswer(Question.of(1));
        assertEquals(1, a2.length);
        assertEquals(1, a2[0]);

        byte[] a3 = Questions.toSimplestAnswer(Question.of(1,1));
        assertEquals(3, a3.length);
        assertEquals(1, a3[0]);
        assertEquals(0, a3[1]);
        assertEquals(1, a3[2]);

        byte[] a4 = Questions.toSimplestAnswer(Question.of(1,2,2));
        assertEquals(7, a4.length);
        assertEquals(1, a4[0]);
        assertEquals(0, a4[1]);
        assertEquals(1, a4[2]);
        assertEquals(1, a4[3]);
        assertEquals(0, a4[4]);
        assertEquals(1, a4[5]);
        assertEquals(1, a4[6]);
    }
}
