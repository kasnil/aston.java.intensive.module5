package aston.java.intensive.module5.utils.validation.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class LengthBetweenValidatorTest {
    private final PropertyValidator validator = new LengthBetweenValidator(2, 5);

    @ParameterizedTest
    @ValueSource(strings = { "\r\n", "Aston" })
    public void testIsValid(String value) {
        assertTrue(this.validator.isValid(value));
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "AAston" })
    public void testIsValidFailed(String value) {
        assertFalse(this.validator.isValid(value));
    }
}
