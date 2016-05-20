package engine.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nerijus
 */
public class StringsTest {

    @Test
    public void testRepeatTimes() throws Exception {
        assertEquals("", Strings.repeat(".", 0));
        assertEquals(".", Strings.repeat(".", 1));
        assertEquals("..", Strings.repeat(".", 2));
        assertEquals("...", Strings.repeat(".", 3));
    }

}