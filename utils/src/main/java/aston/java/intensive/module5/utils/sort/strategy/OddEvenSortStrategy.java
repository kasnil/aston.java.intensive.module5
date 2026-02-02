package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class OddEvenSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return list;
        }

        boolean sorted = false;
        while (!sorted) {
            sorted = performOddSort(list, comparator);
            sorted = performEvenSort(list, comparator) && sorted;
        }

        return list;
    }

    private boolean performOddSort(List<T> list, Comparator<T> comparator) {
        boolean sorted = true;
        for (int i = 1; i < list.size() - 1; i += 2) {
            if (greater(list.get(i), list.get(i + 1), comparator)) {
                swap(list, i, i + 1);
                sorted = false;
            }
        }
        return sorted;
    }

    private boolean performEvenSort(List<T> list, Comparator<T> comparator) {
        boolean sorted = true;
        for (int i = 0; i < list.size() - 1; i += 2) {
            if (greater(list.get(i), list.get(i + 1), comparator)) {
                swap(list, i, i + 1);
                sorted = false;
            }
        }
        return sorted;
    }
}
