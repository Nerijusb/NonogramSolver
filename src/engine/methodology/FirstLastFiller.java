package engine.methodology;

import engine.Puzzle;
import engine.Questions;
import engine.Template;

/**
 * Fills partial answers found on the edges of template
 *
 * @author Nerijus
 */
public class FirstLastFiller extends SolveMethodology {
    public FirstLastFiller(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    public void apply(Template template) {
        fillFirstRow(template);
        fillLastRow(template);
        fillFirstColumn(template);
        fillLastColumn(template);
    }

    private void fillFirstRow(Template template) {
        byte[] row = template.getRaw()[0];
        for (int i = 0; i < puzzle.getWidth(); i++) {
            if (row[i] == Template.SOLVED_CHECK) {
                int number = puzzle.getColumns().get(i).getNumbers().get(0);
                for (int j = 0; j < number; j++) {
                    template.getRaw()[j][i] = Template.SOLVED_CHECK;
                }
                if (number < puzzle.getHeight()) {
                    template.getRaw()[number][i] = Template.SOLVED_EMPTY;
                }
            }
        }
    }

    private void fillLastRow(Template template) {
        byte[] row = template.getRaw()[puzzle.getHeight()-1];
        for (int i = 0; i < puzzle.getWidth(); i++) {
            if (row[i] == Template.SOLVED_CHECK) {
                int number = Questions.lastNumber(puzzle.getColumns().get(i));
                for (int j = puzzle.getHeight()-1; j > puzzle.getHeight()-1-number; j--) {
                    template.getRaw()[j][i] = Template.SOLVED_CHECK;
                }
                if (puzzle.getHeight()-number > 0) {
                    template.getRaw()[puzzle.getHeight()-number-1][i] = Template.SOLVED_EMPTY;
                }
            }
        }
    }

    private void fillFirstColumn(Template template) {
        for (int i = 0; i < puzzle.getHeight(); i++) {
            if (template.getRaw()[i][0] == Template.SOLVED_CHECK) {
                int number = puzzle.getRows().get(i).getNumbers().get(0);
                for (int j = 0; j < number; j++) {
                    template.getRaw()[i][j] = Template.SOLVED_CHECK;
                }
                if (number < puzzle.getWidth()) {
                    template.getRaw()[i][number] = Template.SOLVED_EMPTY;
                }
            }
        }
    }

    private void fillLastColumn(Template template) {
        for (int i = 0; i < puzzle.getHeight(); i++) {
            if (template.getRaw()[i][puzzle.getWidth()-1] == Template.SOLVED_CHECK) {
                int number = Questions.lastNumber(puzzle.getRows().get(i));
                for (int j = puzzle.getWidth()-1; j > puzzle.getWidth()-1-number; j--) {
                    template.getRaw()[i][j] = Template.SOLVED_CHECK;
                }
                if (puzzle.getWidth()-number > 0) {
                    template.getRaw()[i][puzzle.getWidth()-number-1] = Template.SOLVED_EMPTY;
                }
            }
        }
    }
}
