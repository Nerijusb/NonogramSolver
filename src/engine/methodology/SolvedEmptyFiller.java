package engine.methodology;

import engine.Puzzle;
import engine.Template;

/**
 * Checks if there is any lines that have all SOLVED_MARKED
 * but missing some SOLVED_EMPTY
 * @author Nerijus
 */
public class SolvedEmptyFiller extends SolveMethodology {

    public SolvedEmptyFiller(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    public void apply(Template template) {
        forRows((i, q) -> {
            int checkedInRow = template.countCheckedInRow(i);
            int unsolvedInRow = template.countUnsolvedInRow(i);
            if (q.getTotal() == checkedInRow && unsolvedInRow > 0) {
                fillUnsolvedWithCheckedInRow(i, template);
            }
        });
        forColumns((i, q) -> {
            int checkedInColumn = template.countCheckedInColumn(i);
            int unsolvedInColumn = template.countUnsolvedInColumn(i);
            if (q.getTotal() == checkedInColumn && unsolvedInColumn > 0) {
                fillUnsolvedWithCheckedInColumn(i, template);
            }
        });
    }

    private void fillUnsolvedWithCheckedInRow(int index, Template template) {
        for (int i = 0; i < template.getWidth(); i++) {
            if (template.getRaw()[index][i] == Template.UNSOLVED) {
                template.getRaw()[index][i] = Template.SOLVED_EMPTY;
            }
        }
    }


    private void fillUnsolvedWithCheckedInColumn(int index, Template template) {
        for (int i = 0; i < template.getHeight(); i++) {
            if (template.getRaw()[i][index] == Template.UNSOLVED) {
                template.getRaw()[i][index] = Template.SOLVED_EMPTY;
            }
        }
    }
}
