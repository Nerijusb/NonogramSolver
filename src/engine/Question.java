package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Nerijus
 */
public class Question {
    private List<Integer> numbers;

    private Question(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static final Question EMPTY = new Question(Collections.singletonList(0));

    public static Question of(int... numbers) {
        List<Integer> listNumbers = new ArrayList<>();
        for (int n : numbers) {
            if (n > 0) {
                listNumbers.add(n);
            }
        }
        if (listNumbers.isEmpty()) {
            return Question.EMPTY;
        }
        return new Question(listNumbers);
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }

    /**
     * Returns length of the question, e.g. for question [1,5,5,2] is 4
     * @return length, or number count (for empty question [0] is 1)
     */
    public int getLength() {
        return numbers.size();
    }

    /**
     * Safe get of number by index.
     * If not found, returns -1.
     *
     * @param index number index
     * @return number at index or -1 if not found
     */
    public int numberAt(int index) {
        if (numbers.size() <= index) {
            return -1;
        }
        return numbers.get(index);
    }

    /**
     * Checks if two questions are equal, meaning that
     * they have same numbers
     * @param q question to compare to
     * @return true if questions are equal
     */
    public boolean isEqualTo(Question q) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numberAt(i) != q.numberAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns number total
     * @return total
     */
    public int getTotal() {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }
}
