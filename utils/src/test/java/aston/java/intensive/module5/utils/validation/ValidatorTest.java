package aston.java.intensive.module5.utils.validation;

import aston.java.intensive.module5.utils.guard.GuardException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
    @Test
    public void testIsValid() {
        var user = new User("Ivan", "ivan@example.org", "123456");
        var validator = new UserValidator();

        assertTrue(validator.validate(user).isValid());
    }

    @Test
    public void testIsInvalid() {
        var user = new User("Ivan", "ivan@example.org", "12345");
        var validator = new UserValidator();

        var validateResult = validator.validate(user);
        assertEquals(1, validateResult.getErrors().size());
        assertTrue(validateResult.getErrors().stream().map(error  -> error.errorMessage()).anyMatch(error -> error.equals("Пароль должен содержать минимум 6 символов")));
    }

    @Test
    public void testIsValidOrElseThrow() {
        var user = new User("Ivan", "ivan@example.org", "12345");
        var validator = new UserValidator();

        assertThrows(ValidationException.class, () -> validator.validateOrElseThrow(user));
    }

    @Test
    public void testNullInstance() {
        User user = null;
        var validator = new UserValidator();

        assertThrows(GuardException.class, () -> validator.validateOrElseThrow(user));
    }
}

@Validation
final class UserValidator extends Validator<User> {
    public UserValidator() {
        addRule(User::name).notNull().hasLengthBetween(2, 50);
        addRule(User::email).notNull().email();
        addRule(User::password).notNull().hasLengthBetween(6, 255, "Пароль должен содержать минимум 6 символов");
    }
}

record User(String name, String email, String password) { }