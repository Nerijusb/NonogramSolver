package engine;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nerijus
 */
public class TemplatesTest {

    @Test
    public void testDisplayRow() throws Exception {
        assertEquals("?????", Templates.display(new byte[]{-1,-1,-1,-1,-1}));
        assertEquals("     ", Templates.display(new byte[]{0,0,0,0,0}));
        assertEquals("xxxxx", Templates.display(new byte[]{1,1,1,1,1}));
    }
}