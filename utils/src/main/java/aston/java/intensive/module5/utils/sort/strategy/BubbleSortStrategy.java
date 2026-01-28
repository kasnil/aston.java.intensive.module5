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
        for (int i = 0, size = list.size(); i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (greater(list.get(j), list.get(j+1), comparator)) {
                    swap(list, j, j+1);
                }
            }
        }
        return list;
    }
}
