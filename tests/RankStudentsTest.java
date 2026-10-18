
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RankStudentsTest {

    @Test
    void emptyRosterReturnsEmptyList() {
        Roster roster = new Roster();

        assertEquals(0, rank_students(roster).size());
    }
}
