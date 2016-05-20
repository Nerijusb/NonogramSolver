package engine;

/**
 * @author Nerijus
 */
public final class Templates {

    public static final String SOLVED_CHECK_DISPLAY = "x";
    public static final String SOLVED_EMPTY_DISPLAY = " ";
    public static final String UNSOLVED_DISPLAY = "?";

    public static String rowDisplay(Template template, int index) {
        return display(template.getRaw()[index]);
    }

    public static String display(byte[] line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length; i++) {
            if (line[i] == Template.SOLVED_CHECK) {
                sb.append(SOLVED_CHECK_DISPLAY);
            } else if (line[i] == Template.SOLVED_EMPTY) {
                sb.append(SOLVED_EMPTY_DISPLAY);
            } else {
                sb.append(UNSOLVED_DISPLAY);
            }
        }
        return sb.toString();
    }
}
