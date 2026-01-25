package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureIntegerGuardTest {
    @ParameterizedTest
    @ValueSource(ints = { 0, 292 })
    public void testIsonNegative(int value) {
        assertDoesNotThrow(() -> Ensure.that(value).isNonNegative());
    }

    @Test
    public void testIsonNegativeFailed() {
        var value = -292;

        assertThrows(GuardException.class, () -> Ensure.that(value).isNonNegative());
    }
}
