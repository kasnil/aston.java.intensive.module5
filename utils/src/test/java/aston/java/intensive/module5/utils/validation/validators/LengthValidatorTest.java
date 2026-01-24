package aston.java.intensive.module5.utils.validation.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LengthValidatorTest {
    private final PropertyValidator validator = new StringLengthValidator(5);

    @Test
    public void testIsValid() {
        assertTrue(this.validator.validate("Aston").isEmpty());
    }

    @Test
    public void testIsValidFailed() {
        assertTrue(this.validator.validate("").isPresent());
        assertTrue(this.validator.validate(null).isPresent());
        assertTrue(this.validator.validate("AAston").isPresent());
    }
}
