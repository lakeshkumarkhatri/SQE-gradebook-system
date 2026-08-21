import java.util.ArrayList;
import java.util.List;

public class gradebook {
    private String name;
    private String rollNo;
    private List<Double> scores;

    public gradebook(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
        this.scores = new ArrayList<>();
    }

    /**
     * Adds a score to the student's score list.
     *
     * @param score the score to add; must not be negative
     * @throws IllegalArgumentException if the score is negative
     */
    public void addScore(double score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        scores.add(score);
    }
}
