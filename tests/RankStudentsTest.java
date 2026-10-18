
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RankStudentsTest {

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
