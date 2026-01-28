package aston.java.intensive.module5.utils.sort;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy<T> {
    List<T> sort(List<T> list, Comparator<T> comparator);
}
