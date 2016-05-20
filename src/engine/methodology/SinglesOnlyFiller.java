package engine.methodology;

import engine.Puzzle;
import engine.Question;
import engine.Template;

/**
 * Applies to questions consisting of only 1s.
 * It checks of there already is one solved in the line
 * and surrounds it with {@link Template#SOLVED_EMPTY}
 *
 * @author Nerijus
 */
public class SinglesOnlyFiller extends SolveMethodology {

    public SinglesOnlyFiller(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    public void apply(Template template) {
        forRows((index, q) -> fillRow(template, index, q));
        forColumns((index, q) -> fillColumn(template, index, q));
    }

    private void fillRow(Template template, int index, Question q) {
        if (!isApplicable(q)) {
            return;
        }
        byte[] row = template.getRaw()[index];
        for (int i = 0; i < puzzle.getWidth(); i++) {
            if (row[i] == Template.SOLVED_CHECK) {
                // add before
                if (i != 0) {
                    row[i-1] = Template.SOLVED_EMPTY;
                }
                // add after
                if (i != puzzle.getWidth()-1) {
                    row[i+1] = Template.SOLVED_EMPTY;
                }
            }
        }
    }

    private void fillColumn(Template template, int index, Question q) {
        if (!isApplicable(q)) {
            return;
        }
        for (int i = 0; i < puzzle.getHeight(); i++) {
            if (template.getRaw()[i][index] == Template.SOLVED_CHECK) {
                // add before
                if (i != 0) {
                    template.getRaw()[i-1][index] = Template.SOLVED_EMPTY;
                }
                // add after
                if (i != puzzle.getWidth()-1) {
                    template.getRaw()[i+1][index] = Template.SOLVED_EMPTY;
                }
            }
        }
    }

    private boolean isApplicable(Question q) {
        return q.getNumbers().stream()
                .allMatch(n -> n == 1);
    }
}
