package aston.java.intensive.module5.utils.sort;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy<T> {
    List<T> sort(List<T> list, Comparator<T> comparator);

    default boolean less(T o1, T o2, Comparator<T> comparator) {
        return comparator.compare(o1, o2) < 0;
    }

    default boolean greater(T o1, T o2, Comparator<T> comparator) {
        return less(o2, o1, comparator);
    }

    default void swap(List<T> list, int i, int j) {
        if (i != j) {
            final T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
}
