package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureBooleanGuardTest {
    @Test
    public void testIsTrue() {
        var value = true;

        assertDoesNotThrow(() -> Ensure.that(value).isTrue());
    }

    @Test
    public void testIsTrueFailed() {
        var value = false;

        assertThrows(GuardException.class, () -> Ensure.that(value).isTrue());
    }

    @Test
    public void testIsFalse() {
        var value = false;

        assertDoesNotThrow(() -> Ensure.that(value).isFalse());
    }

    @Test
    public void testIsFalseFailed() {
        var value = true;

        assertThrows(GuardException.class, () -> Ensure.that(value).isFalse());
    }
}
