import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

public class RosterTest {

    @Test
    void testZeroScoresInvalid() {
        gradebook student = new gradebook("Student Zero", "BVA-ROSTER-0");
        Roster roster = new Roster();

        assertThrows(
                IllegalArgumentException.class,
                () -> roster.addStudent(student)
        );
    }

    @Test
    void testOneScoreValid() {
        gradebook student = new gradebook("Student One", "BVA-ROSTER-1");
        student.addScore(80);

        Roster roster = new Roster();
        roster.addStudent(student);

        assertEquals(1, roster.getStudentCount());
    }

    @Test
    void testTwoScoresValid() {
        gradebook student = new gradebook("Student Two", "BVA-ROSTER-2");
        student.addScore(80);
        student.addScore(80);

        Roster roster = new Roster();
        roster.addStudent(student);

        assertEquals(1, roster.getStudentCount());
    }

    @Test
    void testFiveScoresValid() {
        gradebook student = new gradebook("Student Five", "BVA-ROSTER-5");

        for (int i = 0; i < 5; i++) {
            student.addScore(80);
        }

        Roster roster = new Roster();
        roster.addStudent(student);

        assertEquals(1, roster.getStudentCount());
    }

    @Test
    void testSixScoresValid() {
        gradebook student = new gradebook("Student Six", "BVA-ROSTER-6");

        for (int i = 0; i < 6; i++) {
            student.addScore(80);
        }

        Roster roster = new Roster();
        roster.addStudent(student);

        assertEquals(1, roster.getStudentCount());
    }

    @Test
    void testSevenScoresInvalid() {
        gradebook student = new gradebook("Student Seven", "BVA-ROSTER-7");

        for (int i = 0; i < 7; i++) {
            student.addScore(80);
        }

        Roster roster = new Roster();

        assertThrows(
                IllegalArgumentException.class,
                () -> roster.addStudent(student)
        );
    }

    @Test
    void testClassAverageEmptyRoster() {
        Roster roster = new Roster();

        assertEquals(0.0, roster.class_average(), 0.001);
    }

    @Test
    void testClassAverageSingleStudent() {
        gradebook student = new gradebook("Student Average", "CLASS-AVG-1");
        student.addScore(80);
        student.addScore(100);

        Roster roster = new Roster();
        roster.addStudent(student);

        assertEquals(90.0, roster.class_average(), 0.001);
    }

    @Test
    void testClassAverageMultipleStudents() {
        gradebook student1 = new gradebook("Student One", "CLASS-AVG-2");
        student1.addScore(80);
        student1.addScore(100);

        gradebook student2 = new gradebook("Student Two", "CLASS-AVG-3");
        student2.addScore(60);
        student2.addScore(80);

        Roster roster = new Roster();
        roster.addStudent(student1);
        roster.addStudent(student2);

        assertEquals(80.0, roster.class_average(), 0.001);
    }

    @Test
    void testSaveToFileSuccessfully() {
        AtomicReference<StringWriter> capturedWriter = new AtomicReference<>();

        Roster roster = new Roster(path -> {
            StringWriter writer = new StringWriter();
            capturedWriter.set(writer);
            return writer;
        });

        gradebook student = new gradebook("File Student", "FILE-TEST-1");
        student.addScore(80);
        roster.addStudent(student);

        roster.save_to_file("unused-test-path.txt");

        String content = capturedWriter.get().toString();

        assertTrue(content.contains("Student Count: 1"));
        assertTrue(content.contains("Class Average: 80.0"));
    }

    @Test
    void testSaveToFileFailure() {
        Roster roster = new Roster(path -> {
            throw new IOException("Simulated write failure");
        });

        GradeBookIOError error = assertThrows(
                GradeBookIOError.class,
                () -> roster.save_to_file("unused-test-path.txt")
        );

        assertTrue(error.getCause() instanceof IOException);
    }
}