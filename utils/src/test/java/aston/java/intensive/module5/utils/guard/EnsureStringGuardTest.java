package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureStringGuardTest {
    @Test
    public void testIsNotNull() {
        String value = "";

        assertDoesNotThrow(() -> Ensure.That(value).isNotNull());
    }

    @Test
    public void testIsNotNullFailed() {
        String value = null;

        assertThrows(IllegalArgumentException.class, () -> Ensure.That(value).isNotNull());
    }

    @Test
    public void testIsNotNullOrEmpty() {
        String value = "Hello, World!";

        assertDoesNotThrow(() -> Ensure.That(value).isNotNullOrEmpty());
    }

    @Test
    public void testIsNotNullOrEmptyFailed() {
        String value = "";

        assertThrows(IllegalArgumentException.class, () -> Ensure.That(value).isNotNullOrEmpty());
    }
}
