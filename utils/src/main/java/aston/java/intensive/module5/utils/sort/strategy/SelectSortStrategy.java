package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class SelectSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return list;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            final int minIndex = findIndexOfMin(list, i, comparator);
            swap(list, i, minIndex);
        }
        return list;
    }

    private int findIndexOfMin(List<T> list, int startIndex, Comparator<T> comparator) {
        int minIndex = startIndex;
        for (int i = startIndex + 1; i < list.size(); i++) {
            if (less(list.get(i), list.get(minIndex), comparator)) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}

