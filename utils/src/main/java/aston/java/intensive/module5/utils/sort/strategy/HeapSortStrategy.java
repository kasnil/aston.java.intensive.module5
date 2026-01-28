package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class HeapSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return list;
        }

        int n = list.size();
        heapify(list, n, comparator);
        while (n > 1) {
            swap(list, 0, n - 1);
            n--;
            siftDown(list, 1, n, comparator);
        }
        return list;
    }

    private void heapify(List<T> list, final int n, Comparator<T> comparator) {
        for (int k = n / 2; k >= 1; k--) {
            siftDown(list, k, n, comparator);
        }
    }

    private void siftDown(List<T> list, int k, int n, Comparator<T> comparator) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(list.get(j - 1), list.get(j), comparator)) {
                j++;
            }
            if (!less(list.get(k - 1), list.get(j - 1), comparator)) {
                break;
            }
            swap(list, k - 1, j - 1);
            k = j;
        }
    }
}

