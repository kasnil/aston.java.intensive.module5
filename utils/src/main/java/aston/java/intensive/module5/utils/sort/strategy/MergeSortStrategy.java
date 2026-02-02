package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return list;
        }
        doSort(list, 0, list.size() - 1, comparator);
        return list;
    }

    private void doSort(List<T> list, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = (left + right) >>> 1;
            doSort(list, left, mid, comparator);
            doSort(list, mid + 1, right, comparator);
            merge(list, left, mid, right, comparator);
        }
    }

    private void merge(List<T> list, int left, int mid, int right, Comparator<T> comparator) {
        int i = left;
        int j = mid + 1;
        List<T> temp = new ArrayList<>(right - left + 1);

        while (i <= mid || j <= right) {
            if (i > mid ||
                    (j <= right && less(list.get(j), list.get(i), comparator))) {
                temp.add(list.get(j++));
            } else {
                temp.add(list.get(i++));
            }
        }
        for (int k = 0; k <= right - left; k++) {
            list.set(left + k, temp.get(k));
        }
    }
}

