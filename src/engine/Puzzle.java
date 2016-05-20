package engine;

import java.util.Arrays;
import java.util.List;

/**
 * Main puzzle class
 * @author Nerijus on 2016.04.12.
 */
public class Puzzle {
    private List<Question> rows;
    private List<Question> columns;

    public Puzzle(List<Question> columns, List<Question> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public static final Puzzle EMPTY = new Puzzle(
            Arrays.asList(Question.EMPTY),
            Arrays.asList(Question.EMPTY));

    public List<Question> getRows() {
        return rows;
    }

    public List<Question> getColumns() {
        return columns;
    }

    public int getHeight() {
        return rows.size();
    }

    public int getWidth() {
        return columns.size();
    }
}
