
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RankStudentsTest {

    @Test
    void equalAveragesAreRankedAlphabetically() {
        Roster roster = new Roster();

        gradebook sara = new gradebook("Sara", "LAB8-201");
        sara.addScore(80);

        gradebook ali = new gradebook("Ali", "LAB8-202");
        ali.addScore(80);

        gradebook ahmed = new gradebook("Ahmed", "LAB8-203");
        ahmed.addScore(80);

        roster.addStudent(sara);
        roster.addStudent(ali);
        roster.addStudent(ahmed);

        assertEquals(
                java.util.List.of(ahmed, ali, sara),
                roster.rank_students()
        );
    }

    @Test
    void studentsAreRankedByAverageDescending() {
        Roster roster = new Roster();

        gradebook ali = new gradebook("Ali", "LAB8-101");
        ali.addScore(60);

        gradebook sara = new gradebook("Sara", "LAB8-102");
        sara.addScore(95);

        gradebook ahmed = new gradebook("Ahmed", "LAB8-103");
        ahmed.addScore(80);

        roster.addStudent(ali);
        roster.addStudent(sara);
        roster.addStudent(ahmed);

        assertEquals(
                java.util.List.of(sara, ahmed, ali),
                roster.rank_students()
        );
    }

    @Test
    void singleStudentReturnsThatStudent() {
        Roster roster = new Roster();

        gradebook student = new gradebook("Ali", "LAB8-001");
        student.addScore(85);
        roster.addStudent(student);

        assertEquals(
                java.util.List.of(student),
                roster.rank_students()
        );
    }

    @Test
    void emptyRosterReturnsEmptyList() {
        Roster roster = new Roster();

        assertEquals(0, roster.rank_students().size());
    }
}
