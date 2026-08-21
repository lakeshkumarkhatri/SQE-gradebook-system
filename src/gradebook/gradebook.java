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

    public void addScore(double score) {
        scores.add(score);
    }
}
