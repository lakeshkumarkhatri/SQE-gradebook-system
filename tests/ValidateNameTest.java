
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidateNameTest {

    @Test
    void testValidTypicalName() {
        gradebook student = new gradebook("Test Student", "NAME-1");
        assertTrue(student.validateName("Ali Khan"));
    }

    @Test
    void testEmptyNameInvalid() {
        gradebook student = new gradebook("Test Student", "NAME-2");
        assertFalse(student.validateName(""));
    }

    @Test
    void testOverLengthNameInvalid() {
        gradebook student = new gradebook("Test Student", "NAME-3");
        String name = "A".repeat(51);

        assertFalse(student.validateName(name));
    }

    @Test
    void testDigitsInvalid() {
        gradebook student = new gradebook("Test Student", "NAME-4");
        assertFalse(student.validateName("Ali123"));
    }

    @Test
    void testSymbolsInvalid() {
        gradebook student = new gradebook("Test Student", "NAME-5");
        assertFalse(student.validateName("Ali@Khan"));
    }

    @Test
    void testNameLengthBoundaries() {
        gradebook student = new gradebook("Length Test", "BVA-NAME-LENGTH");

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
}
