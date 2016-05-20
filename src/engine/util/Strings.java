package engine.util;

/**
 * @author Nerijus
 */
public final class Strings {

    /**
     * Repeats given string number of times.
     * Repeating 0 or 1 times returns the same string
     *
     * @param what string to repeat
     * @param times how msny times
     * @return String
     */
    public static String repeat(String what, int times) {
        if (times == 0) {
            return "";
        }
        if (times == 1) {
            return what;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(what);
        for (int i = 1; i < times; i++) {
            sb.append(what);
        }
        return sb.toString();
    }
}
