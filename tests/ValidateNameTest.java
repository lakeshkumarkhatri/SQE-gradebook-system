
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidateNameTest {

    private gradebook student;
    private static int testId = 0;

    // Function scope: create a fresh student before each test
    // so tests remain independent.
    @BeforeEach
    void setUp() {
        student = new gradebook(
                "Test Student", "NAME-TEST-" + (++testId));
    }

    @Test
    void testValidTypicalName() {
        assertTrue(student.validateName("Ali Khan"));
    }

    @Test
    void testEmptyNameInvalid() {
        assertFalse(student.validateName(""));
    }

    @Test
    void testOverLengthNameInvalid() {
        String name = "A".repeat(51);

        assertFalse(student.validateName(name));
    }

    @Test
    void testDigitsInvalid() {
        assertFalse(student.validateName("Ali123"));
    }

    @Test
    void testSymbolsInvalid() {
        assertFalse(student.validateName("Ali@Khan"));
    }

    @Test
    void testNameLengthBoundaries() {
        String oneChar = "A";
        String fortyNineChars = "A".repeat(49);
        String fiftyChars = "A".repeat(50);
        String fiftyOneChars = "A".repeat(51);

        assertFalse(student.validateName(""));
        assertTrue(student.validateName(oneChar));
        assertTrue(student.validateName(fortyNineChars));
        assertTrue(student.validateName(fiftyChars));
        assertFalse(student.validateName(fiftyOneChars));
    }

    @Test
    void testNameMustContainAtLeastOneLetter() {
        assertFalse(student.validateName("-----"));
        assertFalse(student.validateName("     "));
        assertFalse(student.validateName("- - -"));

        assertTrue(student.validateName("Ali"));
        assertTrue(student.validateName("Ali-Khan"));
    }
}
