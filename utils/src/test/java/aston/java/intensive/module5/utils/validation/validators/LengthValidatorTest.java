package aston.java.intensive.module5.utils.validation.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LengthValidatorTest {
    private final PropertyValidator validator = new LengthValidator(5);

    @Test
    public void testIsValid() {
        assertTrue(this.validator.isValid("Aston"));
    }

    @Test
    public void testIsValidFailed() {
        assertFalse(this.validator.isValid(""));
        assertFalse(this.validator.isValid(null));
        assertFalse(this.validator.isValid("AAston"));
    }
}
