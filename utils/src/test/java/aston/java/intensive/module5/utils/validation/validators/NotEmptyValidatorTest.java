package aston.java.intensive.module5.utils.validation.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotEmptyValidatorTest {
    private final PropertyValidator validator = new StringNotEmptyValidator();

    @ParameterizedTest
    @ValueSource(strings = { "\r\n", "Aston" })
    public void testIsValid(String value) {
        assertTrue(this.validator.validate(value).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = { "" })
    public void testIsValidFailed(String value) {
        assertTrue(this.validator.validate(value).isPresent());
    }
}
