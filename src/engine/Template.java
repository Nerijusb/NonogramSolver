package engine;

/**
 * Template is a grid where all answers are stored.
 * Each cell can be in one of the three states:
 * UNSOLVED (-1) - unknown value
 * SOLVED_EMPTY (0) - solved, must be empty
 * SOLVED_CHECK (1) - solved, must be full (checked)
 *
 * @author Nerijus
 */
public class Template {

    public static final byte UNSOLVED = -1;
    public static final byte SOLVED_EMPTY = 0;
    public static final byte SOLVED_CHECK = 1;

    private int width;
    private int height;
    private byte[][] template;

    public Template(int width, int height) {
        this.width = width;
        this.height = height;
        initTemplate();
    }

    // For testing
    public Template(int width, int height, byte[][] template) {
        this.width = width;
        this.height = height;
        this.template = template;
    }

    public byte[][] getRaw() {
        return template;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void initTemplate() {
        template = new byte[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                template[i][j] = UNSOLVED;
            }
        }
    }

    public int countUnsolved() {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (template[i][j] == UNSOLVED) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countSolved() {
        return (height * width) - countUnsolved();
    }

    public boolean isSolved() {
        return countUnsolved() == 0;
    }

    public int countUnsolvedInRow(int index) {
        return countInRow(index, UNSOLVED);
    }

    public int countCheckedInRow(int index) {
        return countInRow(index, SOLVED_CHECK);
    }

    public int countUnsolvedInColumn(int index) {
        return countInColumn(index, UNSOLVED);
    }

    public int countCheckedInColumn(int index) {
        return countInColumn(index, SOLVED_CHECK);
    }

    public boolean isRowSolved(int index) {
        return countUnsolvedInRow(index) == 0;
    }

    public boolean isColumnSolved(int index) {
        return countUnsolvedInColumn(index) == 0;
    }

    public int countSolvedEmptyInRow(int index) {
        return countInRow(index, SOLVED_EMPTY);
    }

    public int countSolvedEmptyInColumn(int index) {
        return countInColumn(index, SOLVED_EMPTY);
    }

    private int countInColumn(int index, byte what) {
        int count = 0;
        for (int i = 0; i < height; i++) {
            if (template[i][index] == what) {
                count++;
            }
        }
        return count;
    }

    private int countInRow(int index, byte what) {
        int count = 0;
        for (int i = 0; i < width; i++) {
            if (template[index][i] == what) {
                count++;
            }
        }
        return count;
    }
}
