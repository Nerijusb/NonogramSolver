package engine;

/**
 * @author Nerijus
 */
public class Interval {
    int from = -1;
    int to = -1;
    int length = -1;
    byte[] values;
    String display;

    boolean solved;
    boolean partiallySolved;
    boolean unsolved;
    boolean empty;

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getLength() {
        return length;
    }

    public byte[] getValues() {
        return values;
    }

    public String getDisplay() {
        return display;
    }

    public boolean isSolved() {
        return solved;
    }

    public boolean isPartiallySolved() {
        return partiallySolved;
    }

    public boolean isUnsolved() {
        return unsolved;
    }

    public boolean isEmpty() {
        return empty;
    }
}
