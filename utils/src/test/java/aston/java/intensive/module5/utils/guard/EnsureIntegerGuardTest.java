package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureIntegerGuardTest {
    @ParameterizedTest
    @ValueSource(ints = { 0, 292 })
    public void testIsNonNegative(int value) {
        assertDoesNotThrow(() -> Ensure.that(value).isNonNegative());
    }

    @Test
    public void testIsNonNegativeFailed() {
        var value = -292;

        assertThrows(GuardException.class, () -> Ensure.that(value).isNonNegative());
    }

    @Test
    public void testIs() {
        var value = 292;

        assertDoesNotThrow(() -> Ensure.that(value).is(value));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -292 })
    public void testIsFailed(int value) {
        assertThrows(GuardException.class, () -> Ensure.that(292).is(value));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -292 })
    public void testIsNot(int value) {
        assertDoesNotThrow(() -> Ensure.that(292).isNot(value));
    }

    @Test
    public void testIsNotFailed() {
        var value = 292;
        assertThrows(GuardException.class, () -> Ensure.that(value).isNot(value));
    }
}
