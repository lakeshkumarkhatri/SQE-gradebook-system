
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RankStudentsTest {

    @Test
    void emptyRosterReturnsEmptyList() {
        Roster roster = new Roster();

        assertEquals(0, roster.rank_students().size());
    }
}
