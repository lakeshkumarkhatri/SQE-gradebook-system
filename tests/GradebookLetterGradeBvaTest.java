import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GradebookLetterGradeBvaTest {

    @ParameterizedTest
    @CsvSource({
        "0, F",
        "1, F",
        "59, F",
        "60, D",
        "61, D",
        "69, D",
        "70, C",
        "71, C",
        "79, C",
        "80, B",
        "81, B",
        "89, B",
        "90, A",
        "91, A",
        "99, A",
        "100, A"
    })
    void testLetterGradeBoundaries(double score, String expected) {
        gradebook student = new gradebook("BVA Student", "BVA-" + score);
        assertEquals(expected, student.letterGrade(score));
    }

    @Test
    void testBelowMinimumBoundary() {
        gradebook student = new gradebook("BVA Student", "BVA-NEG");
        assertThrows(
            IllegalArgumentException.class,
            () -> student.letterGrade(-1)
        );
    }

    @Test
    void testAboveMaximumBoundary() {
        gradebook student = new gradebook("BVA Student", "BVA-101");
        assertThrows(
            IllegalArgumentException.class,
            () -> student.letterGrade(101)
        );
    }
}