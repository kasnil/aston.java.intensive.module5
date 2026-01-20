package aston.java.intensive.module5.utils.faker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomizerTest {
    private final Randomizer randomizer = new Randomizer();

    @Test
    public void pick_an_enum()
    {
        var f = randomizer.enumValue(Gender.class);
        assertTrue(f instanceof Gender);
    }
}
