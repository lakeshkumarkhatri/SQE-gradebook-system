package gradebook;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Roster {

    private List<gradebook> students;
    private final WriterFactory writerFactory;

    public Roster() {
        this(path -> Files.newBufferedWriter(Path.of(path)));
    }

    Roster(WriterFactory writerFactory) {
        this.students = new ArrayList<>();
        this.writerFactory = writerFactory;
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

    public double class_average() {
        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (gradebook student : students) {
            total += student.average();
        }

        return total / students.size();
    }

    public void save_to_file(String path) {
        try (Writer writer = writerFactory.open(path);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write("Student Count: " + students.size());
            bufferedWriter.newLine();
            bufferedWriter.write("Class Average: " + class_average());
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new GradeBookIOError("Could not save roster", e);
        }
    }

    public List<gradebook> rank_students() {
        List<gradebook> ranked = new ArrayList<>(students);
        ranked.sort(this::compareStudents);
        return ranked;
    }

    /**
     * Calculates the percentile rank of a student within a roster.
     *
     * @param student the student whose percentile is calculated
     * @param roster  the roster used for comparison
     * @return the student's percentile rank
     */
    public double percentile(gradebook student, List<gradebook> roster) {
        if (roster.isEmpty()) {
            return 0.0;
        }

        if (roster.size() == 1) {
            return 100.0;
        }

        int rank = 0;

        for (gradebook other : roster) {
            if (other.average() < student.average()) {
                rank++;
            }
        }

        return (double) rank / (roster.size() - 1) * 100;
    }

    private int compareStudents(gradebook first, gradebook second) {
        int averageComparison = Double.compare(second.average(), first.average());

        if (averageComparison != 0) {
            return averageComparison;
        }

        int nameComparison = first.getName().compareToIgnoreCase(second.getName());

        if (nameComparison != 0) {
            return nameComparison;
        }

        return first.getName().compareTo(second.getName());
    }
}

interface WriterFactory {
    Writer open(String path) throws IOException;
}

class GradeBookIOError extends RuntimeException {
    public GradeBookIOError(String message, Throwable cause) {
        super(message, cause);
    }
}