package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class BubbleSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return list;
        }
        for (int i = 1, size = list.size(); i < size; i++) {
            boolean swapped = false;
            for (int j = 0; j < size - i; j++) {
                if (greater(list.get(j), list.get(j+1), comparator)) {
                    swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return list;
    }
}
