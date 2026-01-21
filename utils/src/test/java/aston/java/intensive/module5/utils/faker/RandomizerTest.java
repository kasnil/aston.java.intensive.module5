package aston.java.intensive.module5.utils.faker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomizerTest {
    private final Randomizer randomizer = new Randomizer();

    @Test
    public void testEnum()
    {
        var f = randomizer.enumValue(Gender.class);
        assertTrue(f instanceof Gender);
    }

    @Test
    public void testPassword()
    {
        var f = randomizer.password(12);
        assertEquals(12, f.length());
    }
}
