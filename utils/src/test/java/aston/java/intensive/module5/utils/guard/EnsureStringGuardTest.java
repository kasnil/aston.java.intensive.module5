package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureStringGuardTest {
    @Test
    public void testIsNotNull() {
        String value = "";

        assertDoesNotThrow(() -> Ensure.that(value).isNotNull());
    }

    @Test
    public void testIsNotNullFailed() {
        String value = null;

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).isNotNull());
    }

    @Test
    public void testIsNotNullOrEmpty() {
        String value = "Hello, World!";

        assertDoesNotThrow(() -> Ensure.that(value).isNotNullOrEmpty());
    }

    @Test
    public void testIsNotNullOrEmptyFailed() {
        String value = "";

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).isNotNullOrEmpty());
    }

    @Test
    public void testHasLength() {
        String value = "123";

        assertDoesNotThrow(() -> Ensure.that(value).hasLength(3));
    }

    @Test
    public void testHasLengthFailed() {
        String value = "";

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).hasLength(3));
    }

    @Test
    public void testHasLengthBetween() {
        String value = "123";

        assertDoesNotThrow(() -> Ensure.that(value).hasLengthBetween(1, 4));
    }

    @Test
    public void testHasLengthBetweenFailed() {
        String value = "";

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).hasLengthBetween(5, 10));
    }

    @Test
    public void testMatches() {
        String value = "123";

        assertDoesNotThrow(() -> Ensure.that(value).matches("\\d+"));
    }

    @Test
    public void testMatchesFailed() {
        String value = "abc";

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).matches("\\d+"));
    }
}
