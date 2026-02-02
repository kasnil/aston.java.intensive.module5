package aston.java.intensive.module5.domain;

import aston.java.intensive.module5.utils.faker.DataSet;
import aston.java.intensive.module5.utils.faker.Gender;
import aston.java.intensive.module5.utils.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private final DataSet dataSet = new DataSet();

    @Test
    void testMissingName() {
        assertThrows(RuntimeException.class, () -> new User.Builder().setEmail(this.dataSet.email()).setPassword(this.dataSet.password(12)).build());
    }

    @Test
    void testMissingEmail() {
        assertThrows(RuntimeException.class, () -> new User.Builder().setName(this.dataSet.firstName(Gender.Male)).setPassword(this.dataSet.password(12)).build());
    }

    @Test
    void testMissingPassword() {
        assertThrows(RuntimeException.class, () -> new User.Builder().setName(this.dataSet.firstName(Gender.Male)).setEmail(this.dataSet.email()).build());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "1", "абвгдеёжзийклмнопрстуфхцчшщъыьэюяабвгдеёжзийклмнопрстуфхцчшщ" })
    void testInvalidName(String name) {
        assertThrows(ValidationException.class, () -> new User.Builder().setName(name).setEmail(this.dataSet.email()).setPassword(this.dataSet.password(12)).build());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "$A12345@example.com", "!#$%&'*+-/=?^_`|~@someDomain.com" })
    void testInvalidEmail(String email) {
        assertThrows(ValidationException.class, () -> new User.Builder().setName(this.dataSet.firstName(Gender.Male)).setEmail(email).setPassword(this.dataSet.password(12)).build());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "12345" })
    void testInvalidPassword(String password) {
        assertThrows(ValidationException.class, () -> new User.Builder().setName(this.dataSet.firstName(Gender.Male)).setEmail(this.dataSet.email()).setPassword(password).build());
    }

    @Test
    void testBuildUser() {
        final String userName = "Андрей";
        final String userEmail = "andrey@example.org";
        final String userPassword = "123456";
        final User user = new User.Builder()
                .setName("Андрей")
                .setEmail("andrey@example.org")
                .setPassword("123456")
                .build();
        assertNotNull(user);
        assertNotNull(user.toString());
        assertEquals(userName, user.getName());
        assertEquals(userEmail, user.getEmail());
        assertEquals(userPassword, user.getPassword());
    }
}
