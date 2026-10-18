
import java.util.ArrayList;
import java.util.List;

public class Roster {

    private List<gradebook> students;

    public Roster() {
        students = new ArrayList<>();
    }

    public void addStudent(gradebook student) {
        int scoreCount = student.getScoreCount();

        if (scoreCount < 1 || scoreCount > 6) {
            throw new IllegalArgumentException(
                    "A student must have between 1 and 6 scores");
        }

        students.add(student);
    }

    public int getStudentCount() {
        return students.size();
    }

    public List<gradebook> rank_students() {
        List<gradebook> ranked = new ArrayList<>(students);
        ranked.sort(this::compareStudents);
        return ranked;
    }

    private int compareStudents(gradebook first, gradebook second) {
        int averageComparison
                = Double.compare(second.average(), first.average());

        if (averageComparison != 0) {
            return averageComparison;
        }

        return first.getName().compareToIgnoreCase(second.getName());
    }
}
