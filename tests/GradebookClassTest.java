import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GradebookClassTest {

    private gradebook gb;

    @BeforeEach
    void setUp() {
        // Reset or initialize before each test if needed
    }

    @Test
    void testConstructorAndDuplicateRollNumber() {
        gradebook student1 = new gradebook("Alice", "R001");
        assertEquals("Alice", student1.getName());

        // Test duplicate roll number exception branch
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new gradebook("Bob", "R001");
        });
        assertTrue(exception.getMessage().contains("Duplicate roll number"));
    }

    @Test
    void testAddScoreAndCount() {
        gradebook student = new gradebook("Charlie", "R002");
        assertEquals(0, student.getScoreCount());

        student.addScore(85.0);
        student.addScore(90.5);
        assertEquals(2, student.getScoreCount());

        // Test invalid scores (negative, NaN, infinite)
        assertThrows(IllegalArgumentException.class, () -> student.addScore(-5.0));
        assertThrows(IllegalArgumentException.class, () -> student.addScore(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> student.addScore(Double.POSITIVE_INFINITY));
    }

    @Test
    void testAverageScore() {
        gradebook student = new gradebook("David", "R003");
        // Test empty scores average
        assertEquals(0.0, student.average());

        student.addScore(80.0);
        student.addScore(100.0);
        assertEquals(90.0, student.average(), 0.001);
    }

    @Test
    void testLetterGradeBranches() {
        gradebook student = new gradebook("Eve", "R004");

        // Valid ranges
        assertEquals("F", student.letterGrade(50.0));
        assertEquals("D", student.letterGrade(65.0));
        assertEquals("C", student.letterGrade(75.0));
        assertEquals("B", student.letterGrade(85.0));
        assertEquals("A", student.letterGrade(95.0));

        // Invalid score ranges throwing exceptions
        assertThrows(IllegalArgumentException.class, () -> student.letterGrade(-1.0));
        assertThrows(IllegalArgumentException.class, () -> student.letterGrade(101.0));
    }

    @Test
    void testValidateName() {
        gradebook student = new gradebook("Frank", "R005");

        // Valid names
        assertTrue(student.validateName("John Doe"));
        assertTrue(student.validateName("Mary-Jane"));

        // Invalid names (null, empty, too long, invalid characters)
        assertFalse(student.validateName(null));
        assertFalse(student.validateName(""));
        assertFalse(student.validateName("A".repeat(51))); // Over 50 chars
        assertFalse(student.validateName("John123")); // Numbers not allowed
    }
}