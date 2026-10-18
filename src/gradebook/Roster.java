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
}

interface WriterFactory {
    Writer open(String path) throws IOException;
}

class GradeBookIOError extends RuntimeException {
    public GradeBookIOError(String message, Throwable cause) {
        super(message, cause);
    }
}