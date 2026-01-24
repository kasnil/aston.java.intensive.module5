package aston.java.intensive.module5.utils.validation.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NotNullValidatorTest {
    private final PropertyValidator validator = new NotNullValidator();

    @ParameterizedTest
    @ValueSource(strings = { "", "\r\n", "Aston" })
    public void testIsValid(String value) {
        assertTrue(this.validator.validate(value).isEmpty());
    }

    @Test
    public void testIsValidFailed() {
        assertTrue(this.validator.validate(null).isPresent());
    }
}
