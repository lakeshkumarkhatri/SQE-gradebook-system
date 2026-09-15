import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}