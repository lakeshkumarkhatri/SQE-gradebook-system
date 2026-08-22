import java.util.ArrayList;
import java.util.List;

public class gradebook {
    private String name;
    private String rollNo;
    private List<Double> scores;

    public gradebook(String name, String studentId) {
        this.name = name;
        this.rollNo = studentId;
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
            throw new IllegalArgumentException("Score must be a finite non-negative value");
        }
        scores.add(scoreValue);
    }
}
