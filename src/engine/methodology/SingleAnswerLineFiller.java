package engine.methodology;

import engine.Puzzle;
import engine.Question;
import engine.Questions;
import engine.Template;

/**
 * Fills only those lines that have only single possible answer
 * e.g. if we have puzzle 3x3 and question [3] then the only
 * possible outcome is {1,1,1}. This also applies to questions
 * with multiple numbers, like [1,1] then there is only one solution {1,0,1}
 */
public class SingleAnswerLineFiller extends SolveMethodology {

    public SingleAnswerLineFiller(Puzzle puzzle) {
        super(puzzle);
    }

    @Override
    public void apply(Template template) {
        forRows((i, q) -> {
            if (Questions.minLineLength(q) == puzzle.getWidth()) {
                fillRow(i, q, template);
            }
        });
        forColumns((i, q) -> {
            if (Questions.minLineLength(q) == puzzle.getHeight()) {
                fillColumn(i, q, template);
            }
        });
    }

    private void fillRow(int index, Question q, Template template) {
        byte[] answer = Questions.toSimplestAnswer(q);
        for (int i = 0; i < answer.length; i++) {
            template.getRaw()[index][i] = answer[i];
        }
    }

    private void fillColumn(int index, Question q, Template template) {
        byte[] answer = Questions.toSimplestAnswer(q);
        for (int i = 0; i < answer.length; i++) {
            template.getRaw()[i][index] = answer[i];
        }
    }
}