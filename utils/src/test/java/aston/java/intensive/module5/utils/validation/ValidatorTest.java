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
        addRule(User::getName).notNull().hasLengthBetween(2, 50);
        addRule(User::getEmail).notNull().email();
        addRule(User::getPassword).notNull().hasLengthBetween(6, 255, "Пароль должен содержать минимум 6 символов");
    }
}

class User {
    private final String name;
    private final String email;
    private final String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}