package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureAnyGuardTest {
    @Test
    public void testIsNotNull() {
        String value = "";

        assertDoesNotThrow(() -> Ensure.that(value).isNotNull());
    }

    @Test
    public void testIsNotNullFailed() {
        String value = null;

        assertThrows(GuardException.class, () -> Ensure.that(value).isNotNull());
    }
}
