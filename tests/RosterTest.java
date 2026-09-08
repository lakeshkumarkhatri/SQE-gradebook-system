import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RosterTest {

    @Test
    void testZeroScoresInvalid() {
        gradebook student = new gradebook("Student Zero", "ROSTER-0");

        Roster roster = new Roster();

        assertThrows(
            IllegalArgumentException.class,
            () -> roster.addStudent(student)
        );
    }

    @Test
    void testThreeScoresValid() {
        gradebook student = new gradebook("Student Three", "ROSTER-3");

        student.addScore(70);
        student.addScore(80);
        student.addScore(90);

        Roster roster = new Roster();
        roster.addStudent(student);

        assertEquals(1, roster.getStudentCount());
    }

    @Test
    void testEightScoresInvalid() {
        gradebook student = new gradebook("Student Eight", "ROSTER-8");

        for (int i = 0; i < 8; i++) {
            student.addScore(80);
        }

        Roster roster = new Roster();

        assertThrows(
            IllegalArgumentException.class,
            () -> roster.addStudent(student)
        );
    }
}