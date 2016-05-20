package engine;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nerbui on 2016.04.12.
 */
public class QuestionTest {

    @Test
    public void testGetLength() throws Exception {
        assertEquals(1, Question.EMPTY.getLength());
        assertEquals(1, Question.of(1).getLength());
        assertEquals(2, Question.of(1,1).getLength());
        assertEquals(3, Question.of(2,5,1).getLength());
    }

    @Test
    public void testZerosDoesNotGrowLength() {
        assertEquals(1, Question.of(1,0,0).getLength());
        assertEquals(1, Question.of(0,1,0,0).getLength());
        assertEquals(3, Question.of(1,1,0,1).getLength());
    }

    @Test
    public void testNumberAt() throws Exception {
        Question q = Question.of(5,2,3,1);
        assertEquals(5, q.numberAt(0));
        assertEquals(2, q.numberAt(1));
        assertEquals(3, q.numberAt(2));
        assertEquals(1, q.numberAt(3));
        assertEquals(-1, q.numberAt(4));
    }

    @Test
    public void testIsEqualTo() throws Exception {
        assertTrue(Question.EMPTY.isEqualTo(Question.EMPTY));
        assertTrue(Question.of(1).isEqualTo(Question.of(1)));
        assertTrue(Question.of(1,1,2).isEqualTo(Question.of(1,1,2)));
        assertFalse(Question.of(1).isEqualTo(Question.of(2)));
        assertFalse(Question.of(1,2).isEqualTo(Question.of(2,1)));
        assertFalse(Question.of(1,1,2).isEqualTo(Question.of(1,1,3)));
    }
}