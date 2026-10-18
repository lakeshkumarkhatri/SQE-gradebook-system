
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class GradebookLetterGradeTest {

    // Class scope: runs once before all tests in this class.
    @BeforeAll
    static void setUpClass() {
        System.out.println("Preparing letter-grade test class");
    }

    @ParameterizedTest
    @CsvSource({
        "45, F",
        "65, D",
        "75, C",
        "85, B",
        "95, A"
    })
    void testLetterGradeValidClasses(double score, String expected) {
        gradebook student = new gradebook("Test Student", "LG-" + score);
        assertEquals(expected, student.letterGrade(score));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10, 150})
    void testLetterGradeInvalidClasses(double score) {
        gradebook student = new gradebook("Test Student", "INVALID-" + score);

        assertThrows(
            IllegalArgumentException.class,
            () -> student.letterGrade(score)
        );
    }
}
