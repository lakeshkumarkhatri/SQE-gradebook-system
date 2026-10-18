import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AddScoreTest {

    @ParameterizedTest(name = "Valid score: {0}")
    @ValueSource(doubles = {0, 1, 50, 75, 99, 100})
    void testValidScores(double score) {
        gradebook student = new gradebook(
                "Valid Score Student", "VALID-" + score);

        student.addScore(score);

        assertEquals(1, student.getScoreCount());
    }

    @ParameterizedTest(name = "Invalid score: {0}")
    @ValueSource(doubles = {-1, -10, -100, Double.NaN,
                            Double.POSITIVE_INFINITY,
                            Double.NEGATIVE_INFINITY})
    void testInvalidScores(double score) {
        gradebook student = new gradebook(
                "Invalid Score Student", "INVALID-SCORE-" + score);

        assertThrows(
                IllegalArgumentException.class,
                () -> student.addScore(score));

        assertEquals(0, student.getScoreCount());
    }
}