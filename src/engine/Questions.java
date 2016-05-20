package engine;

import engine.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Nerijus
 */
public final class Questions {

    public static int maxLengthOf(List<Question> qs) {
        return qs.stream()
                .map(rQ -> rQ.getLength())
                .max(Integer::compareTo)
                .get();
    }

    /**
     * Returns minimal line length in which answer to the given question could fit
     * e.g. for question [2,2] minimal length would be 5 and minimal answer {1,1,0,1,1}
     * @param q question
     * @return minimal length
     */
    public static int minLineLength(Question q) {
        /*if (q.getNumbers().isEmpty()) {
            return 0;
        }
        int total = q.getNumbers().size() - 1;
        for (int n : q.getNumbers()) {
            total += n;
        }
        return total;*/
        return toSimplestAnswer(q).length;
    }

    public static byte[] toSimplestAnswer(Question q) {
        List<Byte> answer = new ArrayList<>();
        for (int i = 0; i < q.getNumbers().size(); i++) {
            for (int j = 0; j < q.getNumbers().get(i); j++) {
                answer.add((byte)1);
            }
            if (answer.isEmpty() || i != q.getNumbers().size() - 1) {
                answer.add((byte)0);
            }
        }

        byte[] arr = new byte[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            arr[i] = answer.get(i);
        }
        return arr;
    }

    public static String toString(Question q, int offset) {
        StringBuilder sb = new StringBuilder();
        sb.append(Strings.repeat(" ", offset));
        for (int n : q.getNumbers()) {
            if (n == 10) {// TODO: print numbers 10+
                sb.append('a');
            } else {
                sb.append(n);
            }
        }
        return sb.toString();
    }

    /**
     * Makes {@link Question} from answered line (row/column)
     */
    public static Question toQuestion(byte[] answer) {
        return new QuestionMaker().build(answer);
    }

    /**
     * Returns last number in the question
     * @param q question
     * @return number
     */
    public static int lastNumber(Question q) {
        int size = q.getNumbers().size();
        return q.getNumbers().get(size-1);
    }

    private static class QuestionMaker {
        private Stack<Integer> stack = new Stack<>();

        Question build(byte[] answer) {
            for (int i = 0; i < answer.length; i++) {
                push(answer[i]);
            }
            return get();
        }

        /**
         * Field can be 1 or 0
         * @param field
         */
        private void push(byte field) {
            assert field == 1 || field == 0;
            if (field == 0) {
                handleZero();
            } else {
                handleOne();
            }
        }

        private void handleZero() {
            if (stack.isEmpty() || stack.peek() >= 1) {
                stack.push(0);
            }
            return;
        }

        private void handleOne() {
            if (stack.isEmpty()) {
                stack.push(1);
            } else {
                int number = stack.pop();
                stack.push(number + 1);
            }
        }

        private Question get() {
            int numberCount = stack.size();
            int[] numbers = new int[numberCount];
            for (int i = numberCount-1; i >= 0; i--) {
                numbers[i] = stack.elementAt(i);
            }

            return Question.of(numbers);
        }
    }
}