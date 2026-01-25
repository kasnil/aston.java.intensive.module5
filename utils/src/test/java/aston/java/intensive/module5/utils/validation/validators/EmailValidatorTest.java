package aston.java.intensive.module5.utils.validation.validators;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {
    private final PropertyValidator validator = new EmailValidator();

    @ParameterizedTest
    @ValueSource(strings = { "testperson@gmail.com", "TestPerson@gmail.com", "test.person@gmail.com" })
    public void testIsValid(String email) {
        assertTrue(this.validator.validate(email).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "$A12345@example.com", "!#$%&'*+-/=?^_`|~@someDomain.com" })
    public void testIsValidFailed(String email) {
        assertTrue(this.validator.validate(email).isPresent());
    }
}
