package engine.print;

import engine.Puzzle;
import engine.Questions;
import engine.Question;
import engine.Template;
import engine.util.Strings;

import java.util.List;

/**
 * Prints puzzle with template to console
 * @author Nerijus
 */
public class PuzzlePrinter {

    public static final String MARKED = "x";
    public static final String EMPTY = " ";
    public static final String UNKNOWN = "?";

    private final Puzzle puzzle;

    public PuzzlePrinter(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void print(Template result) {
        int offset = Questions.maxLengthOf(puzzle.getRows());
        printColumnQuestions(puzzle.getColumns(), offset);
        printTopLine(puzzle.getWidth(), offset);
        for (int i = 1; i <= puzzle.getHeight(); i++) {
            printRow(i, puzzle.getRows().get(i-1), offset, puzzle.getWidth(), result);
        }
        printBottomLine(puzzle.getWidth(), offset);
    }

    private void printColumnQuestions(List<Question> columns, int offset) {
        int maxLength = Questions.maxLengthOf(columns);
        for (int i = 0; i < maxLength; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(Strings.repeat(" ", offset));
            sb.append(" ");// for 1st horizontal line
            for (Question q : columns) {
                int dif = maxLength - q.getLength();
                if (i-dif < 0) {
                    sb.append(" ");
                } else {
                    sb.append(q.numberAt(i-dif) == -1? " " : numberToString(q.numberAt(i-dif)));
                }
            }
            sb.append(" ");// for 2nd horizontal line
            doPrint(sb.toString());
        }
    }

    private String numberToString(int number) {
        if (number == 10) {// TODO: print numbers 10+
            return "a";
        } else {
            return "" + number;
        }
    }

    private void printBottomLine(int width, int offset) {
        printTopBottomBorder(width, offset);
    }

    private void printTopLine(int width, int offset) {
        printTopBottomBorder(width, offset);
    }

    private void printTopBottomBorder(int width, int offset) {
        StringBuilder sb = new StringBuilder();
        sb.append(Strings.repeat(" ", offset));
        sb.append("|");
        for (int i = 0; i <= width-1; i++) {
            sb.append("-");
        }
        sb.append("|");
        doPrint(sb.toString());
    }

    private void printRow(int index, Question question, int maxRowQuestionLength, int puzzleWidth, Template result) {
        StringBuilder sb = new StringBuilder();
        sb.append(Questions.toString(question, maxRowQuestionLength - question.getNumbers().size()));
        sb.append("|");
        for (int i = 0; i <= puzzleWidth-1; i++) {
            byte value = result.getRaw()[index - 1][i];
            sb.append(value == -1? UNKNOWN : value == 1? MARKED : EMPTY);
        }
        sb.append("|");
        doPrint(sb.toString());
    }

    protected void doPrint(String row) {
        System.out.println(row);
    }
}
