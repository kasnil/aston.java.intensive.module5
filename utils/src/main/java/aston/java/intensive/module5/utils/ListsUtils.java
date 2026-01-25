package aston.java.intensive.module5.utils;

import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public final class ListsUtils {
    public static <E extends Object> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    public static <E extends Object> ArrayList<E> newArrayList(E... elements) {
        Ensure.that(elements).isNotNull();

        int capacity = computeArrayListCapacity(elements.length);
        ArrayList<E> list = new ArrayList<>(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    public static <E extends Object> ArrayList<E> newArrayList(
            Iterator<? extends E> elements) {
        ArrayList<E> list = new ArrayList<>();
        while (elements.hasNext()) {
            list.add(elements.next());
        }
        return list;
    }

    public static <E extends Object> ArrayList<E> newArrayList(
            Iterable<? extends E> elements) {
        Ensure.that(elements).isNotNull();

        return (elements instanceof Collection)
                ? new ArrayList<>((Collection<? extends E>) elements)
                : newArrayList(elements.iterator());
    }

    private static int computeArrayListCapacity(int arraySize) {
        Ensure.that(arraySize).isNonNegative();

        return IntegerUtils.saturatedCast(5L + arraySize + (arraySize / 10));
    }
}
