package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class QuickSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return list;
        }
        quickSort(list, 0, list.size() - 1, comparator);
        return list;
    }

    private void quickSort(List<T> list, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            final int pivotIndex = partition(list, low, high, comparator);
            quickSort(list, low, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex, high, comparator);
        }
    }

    private int partition(List<T> list, int low, int high, Comparator<T> comparator) {
        final int mid = (low + high) >>> 1;
        final T pivot = list.get(mid);

        while (low <= high) {
            while (less(list.get(low), pivot, comparator)) {
                ++low;
            }

            while (greater(list.get(high), pivot, comparator)) {
                --high;
            }

            if (low <= high) {
                swap(list, low, high);
                ++low;
                --high;
            }
        }
        return low;
    }
}

