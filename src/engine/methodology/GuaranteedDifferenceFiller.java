package engine.methodology;

import engine.Puzzle;
import engine.Question;
import engine.Questions;
import engine.Template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solves all the cells that can not be empty because of the question length.
 * For example: if we have question [3] for a puzzle of dimension 5, then cell
 * in the middle must be checked because there is no way to mark 3 cells in a
 * row without filling the middle. All possible answers would be:
 *
 * {1,1,1,0,0}
 * {0,1,1,1,0}
 * {0,0,1,1,1}
 *
 * so it's safe to say {-1,-1,1,-1,-1} is correct for all of them
 * @author Nerijus
 */
public class GuaranteedDifferenceFiller extends SolveMethodology {

    public GuaranteedDifferenceFiller(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    public void apply(Template template) {
        forRows((index, q) -> fillRow(template, index, q));
        forColumns((index, q) -> fillColumn(template, index, q));
    }

    private void fillRow(Template template, int index, Question q) {
        List<Integer> matching = findMatching(q, puzzle.getWidth());
        if (!matching.isEmpty()) {
            markRow(template, index, matching);
        }
    }

    private void fillColumn(Template template, int index, Question q) {
        List<Integer> matching = findMatching(q, puzzle.getHeight());
        if (!matching.isEmpty()) {
            markColumn(template, index, matching);
        }
    }

    private List<Integer> findMatching(Question q, int dimension) {
        byte[] simpleAnswer = Questions.toSimplestAnswer(q);
        if (!isApplicable(simpleAnswer.length, dimension)) {
            return Collections.emptyList();
        }
        byte[] fromLeft = addEmptyToEnd(simpleAnswer, dimension);
        byte[] fromRight = addEmptyToFront(simpleAnswer, dimension);

        return findMatching(wrap(fromLeft), wrap(fromRight));
    }

    private List<Integer> findMatching(List<CellWrapper> fromLeft, List<CellWrapper> fromRight) {
        assert fromLeft.size() == fromRight.size();

        List<Integer> matching = new ArrayList<>();
        for (int i = 0; i < fromLeft.size(); i++) {
            if (fromLeft.get(i).checked && fromRight.get(i).checked
                    && fromLeft.get(i).identifier == fromRight.get(i).identifier) {
                matching.add(i);
            }
        }
        return matching;
    }

    private void markRow(Template template, int index, List<Integer> toMark) {
        for (int i = 0; i < puzzle.getWidth(); i++) {
            if (toMark.contains(i)) {
                template.getRaw()[index][i] = Template.SOLVED_CHECK;
            }
        }
    }

    private void markColumn(Template template, int index, List<Integer> toMark) {
        for (int i = 0; i < puzzle.getHeight(); i++) {
            if (toMark.contains(i)) {
                template.getRaw()[i][index] = Template.SOLVED_CHECK;
            }
        }
    }

    private static class CellWrapper {
        int position;
        boolean checked;
        int identifier = -1;
        CellWrapper(int position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "[pos:"+position+
                    ",chk:"+(checked?"Y":"N")+
                    ",id:"+identifier+"]";
        }
    }

    private List<CellWrapper> wrap(byte[] line) {
        List<CellWrapper> wraps = new ArrayList<>();
        int identifier = 0;
        for (int i = 0; i < line.length; i++) {
            byte cellResult = line[i];

            if (cellResult == Template.SOLVED_EMPTY &&
                    isLastChecked(wraps)) {
                identifier++;
            }

            CellWrapper wrap = new CellWrapper(i);
            if (cellResult == Template.SOLVED_CHECK) {
                wrap.checked = true;
                wrap.identifier = identifier;
            } else {
                wrap.checked = false;
            }
            wraps.add(wrap);
        }

        return wraps;
    }

    private boolean isLastChecked(List<CellWrapper> wraps) {
        return !wraps.isEmpty() && wraps.get(wraps.size() - 1).checked;
    }

    private boolean isApplicable(int answerLength, int puzzleDimension) {
        return answerLength < puzzleDimension &&
                2 * answerLength > puzzleDimension;
    }

    private byte[] addEmptyToEnd(byte[] bytes, int size) {
        byte[] newArr = new byte[size];
        for (int i = 0; i < size; i++) {
            if (i < bytes.length) {
                newArr[i] = bytes[i];
            } else {
                newArr[i] = Template.SOLVED_EMPTY;
            }
        }
        return newArr;
    }

    private byte[] addEmptyToFront(byte[] bytes, int size) {
        byte[] newArr = new byte[size];
        int offset = size - bytes.length;
        for (int i = 0; i < size; i++) {
            if (i < offset) {
                newArr[i] = Template.SOLVED_EMPTY;
            } else {
                newArr[i] = bytes[i-offset];
            }
        }
        return newArr;
    }
}
