package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureBooleanGuardTest {
    @Test
    public void testIsTrue() {
        var value = true;

        assertDoesNotThrow(() -> Ensure.That(value).isTrue());
    }

    @Test
    public void testIsTrueFailed() {
        var value = false;

        assertThrows(IllegalArgumentException.class, () -> Ensure.That(value).isTrue());
    }

    @Test
    public void testIsFalse() {
        var value = false;

        assertDoesNotThrow(() -> Ensure.That(value).isFalse());
    }

    @Test
    public void testIsFalseFailed() {
        var value = true;

        assertThrows(IllegalArgumentException.class, () -> Ensure.That(value).isFalse());
    }
}
