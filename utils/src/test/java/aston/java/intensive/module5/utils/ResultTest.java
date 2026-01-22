package aston.java.intensive.module5.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {
    @Test
    public void testIsOk() {
        Result<String> value = Result.ok("");

        assertTrue(value.isOk());
    }

    @Test
    public void testIsErr() {
        Result<String> value = Result.err(new NullPointerException());

        assertTrue(value.isErr());
    }
}
