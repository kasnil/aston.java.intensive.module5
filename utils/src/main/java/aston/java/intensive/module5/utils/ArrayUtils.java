package aston.java.intensive.module5.utils;

import java.lang.reflect.Array;
import java.util.Collection;

public final class ArrayUtils {
    public static <T extends Object> T[] toArray(Collection<T> collection, Class<T> collectionClass) {
        int size = collection.size();
        var array = newArray(collectionClass, size);
        fillArray(collection, array);
        return array;
    }

    public static <T extends Object> T[] toArray(
            Iterable<? extends T> iterable, Class<T> type) {
        return toArray(iterable, newArray(type, 0));
    }

    public static <T extends Object> T[] newArray(Class<T> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }

    private static <T extends Object> T[] toArray(Iterable<? extends T> iterable, T[] array) {
        Collection<? extends T> collection = castOrCopyToCollection(iterable);
        return collection.toArray(array);
    }

    private static <E extends Object> Collection<E> castOrCopyToCollection(Iterable<E> iterable) {
        return (iterable instanceof Collection)
                ? (Collection<E>) iterable
                : ListsUtils.newArrayList(iterable.iterator());
    }

    private static Object[] fillArray(Collection<?> elements, Object[] array) {
        int i = 0;
        for (Object element : elements) {
            array[i++] = element;
        }
        int size = elements.size();
        if (array.length > size) {
            Object[] unsoundlyCovariantArray = array;
            unsoundlyCovariantArray[size] = null;
        }
        return array;
    }
}
