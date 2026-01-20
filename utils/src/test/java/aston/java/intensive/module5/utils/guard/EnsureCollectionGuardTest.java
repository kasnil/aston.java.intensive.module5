package aston.java.intensive.module5.utils.guard;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EnsureCollectionGuardTest {
    @Test
    public void testArrayIsHasItems() {
        String[] value = new String[]{""};

        assertDoesNotThrow(() -> Ensure.that(value).hasItems());
    }

    @Test
    public void testArrayIsNotNullFailed() {
        String[] value = new String[]{};

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).hasItems());
    }

    @Test
    public void testListIsHasItems() {
        List<String> value = new ArrayList<>(Arrays.asList(""));

        assertDoesNotThrow(() -> Ensure.that(value).hasItems());
    }

    @Test
    public void testListIsNotNullFailed() {
        List<String> value = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).hasItems());
    }

    @Test
    public void testSequencedCollectionIsHasItems() {
        SequencedCollection<String> value = new ArrayList<>(Arrays.asList(""));

        assertDoesNotThrow(() -> Ensure.That(value).hasItems());
    }

    @Test
    public void testSequencedCollectionIsNotNullFailed() {
        SequencedCollection<String> value = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> Ensure.That(value).hasItems());
    }

    @Test
    public void testMapIsHasItems() {
        Map<String, String> value = Map.of(
                " ", " "
        );

        assertDoesNotThrow(() -> Ensure.that(value).hasItems());
    }

    @Test
    public void testMapIsNotNullFailed() {
        Map<String, String> value = Map.of();

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).hasItems());
    }

    public void testSetIsHasItems() {
        Set<String> value = Set.of(" ");

        assertDoesNotThrow(() -> Ensure.that(value).hasItems());
    }

    @Test
    public void testSetIsNotNullFailed() {
        Set<String> value = Set.of();

        assertThrows(IllegalArgumentException.class, () -> Ensure.that(value).hasItems());
    }
}
