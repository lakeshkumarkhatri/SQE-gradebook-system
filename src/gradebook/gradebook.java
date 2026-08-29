import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class gradebook {
    private String name;
    private String rollNo;
    private List<Double> scores;

    private static Set<String> rollNumbers = new HashSet<>();

    public gradebook(String name, String idNumber) {
        if (rollNumbers.contains(idNumber)) {
            throw new IllegalArgumentException("Duplicate roll number: " + idNumber);
        }

        rollNumbers.add(idNumber);

        this.name = name;
        this.rollNo = idNumber;
        this.scores = new ArrayList<>();
    }

    /**
     * Adds a score to the student's score list.
     *
     * @param scoreValue the score to add; must be finite and non-negative
     * @throws IllegalArgumentException if the score is negative, NaN, or infinite
     */
    public void addScore(double scoreValue) {
        if (scoreValue < 0 || !Double.isFinite(scoreValue)) {
            throw new IllegalArgumentException(
                    "Score must be a finite non-negative value");
        }

        scores.add(scoreValue);
    }

    public double average() {
        if (scores.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (double score : scores) {
            sum += score;
        }

        return sum / scores.size();
    }
}
