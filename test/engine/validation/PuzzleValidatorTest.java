package engine.validation;

import engine.Puzzle;
import engine.validation.PuzzleValidator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nerijus
 */
public class PuzzleValidatorTest {
    @Test
    public void testEmptyPuzzleIsValid() throws Exception {
        Puzzle puzzle = Puzzle.EMPTY;
        assertTrue(PuzzleValidator.isValid(puzzle));
    }
}