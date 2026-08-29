import java.util.ArrayList;
import java.util.List;

public class gradebook {
    private String name;
    private String rollNo;
    private List<Double> scores;

    public gradebook(String name, String idNumber) {
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
