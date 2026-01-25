package aston.java.intensive.module5.utils.validation.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MatchesValidatorTest {
    private final PropertyValidator validator = new StringMatchesValidator("^[A-Za-z]+\\d*$");

    @ParameterizedTest
    @ValueSource(strings = { "ABC", "ABC123" })
    public void testIsValid(String value) {
        assertTrue(this.validator.validate(value).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "123ABC", "!#$%&'*+-/=?^_`|~" })
    public void testIsValidFailed(String value) {
        assertTrue(this.validator.validate(value).isPresent());
    }
}
