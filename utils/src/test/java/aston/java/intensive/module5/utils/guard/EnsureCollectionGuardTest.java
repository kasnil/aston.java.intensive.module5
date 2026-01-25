package aston.java.intensive.module5.utils.guard;

import aston.java.intensive.module5.utils.ListsUtils;
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

        assertThrows(GuardException.class, () -> Ensure.that(value).hasItems());
    }

    @Test
    public void testListIsHasItems() {
        List<String> value = ListsUtils.newArrayList("");

        assertDoesNotThrow(() -> Ensure.that(value).hasItems());
    }

    @Test
    public void testListIsNotNullFailed() {
        List<String> value = ListsUtils.newArrayList();

        assertThrows(GuardException.class, () -> Ensure.that(value).hasItems());
    }

    @Test
    public void testSequencedCollectionIsHasItems() {
        SequencedCollection<String> value = ListsUtils.newArrayList("");

        assertDoesNotThrow(() -> Ensure.That(value).hasItems());
    }

    @Test
    public void testSequencedCollectionIsNotNullFailed() {
        SequencedCollection<String> value = ListsUtils.newArrayList();

        assertThrows(GuardException.class, () -> Ensure.That(value).hasItems());
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

        assertThrows(GuardException.class, () -> Ensure.that(value).hasItems());
    }

    public void testSetIsHasItems() {
        Set<String> value = Set.of(" ");

        assertDoesNotThrow(() -> Ensure.that(value).hasItems());
    }

    @Test
    public void testSetIsNotNullFailed() {
        Set<String> value = Set.of();

        assertThrows(GuardException.class, () -> Ensure.that(value).hasItems());
    }
}
