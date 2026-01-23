package aston.java.intensive.module5.utils;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {
    @Test
    public void testIsOk() {
        Result<String> value = Result.ok("");

        assertTrue(value.isOk());
        assertDoesNotThrow(() -> value.orElseThrow());
        assertDoesNotThrow(() -> value.orElseThrow(NoSuchElementException::new));
        assertEquals("", value.orElse("12"));
        assertEquals("", value.orElseGet(() -> "12"));
    }

    @Test
    public void testIsErr() {
        Result<String> value = Result.err(new NullPointerException());

        assertTrue(value.isErr());
        assertThrows(NullPointerException.class, () -> value.orElseThrow());
        assertThrows(NoSuchElementException.class, () -> value.orElseThrow(NoSuchElementException::new));
        assertEquals("12", value.orElse("12"));
        assertEquals("12", value.orElseGet(() -> "12"));
    }

    @Test
    public void testEmpty() {
        ResultEmpty empty = ResultEmpty.ok();
        assertTrue(empty.equals(ResultEmpty.ok()));
        assertTrue(ResultEmpty.ok().equals(empty));

        assertTrue(empty.isOk());
        assertEquals(0, empty.hashCode());
    }
}
