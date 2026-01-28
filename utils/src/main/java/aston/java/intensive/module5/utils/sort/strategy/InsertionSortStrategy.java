package aston.java.intensive.module5.utils.sort.strategy;

import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class InsertionSortStrategy<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() < 2) {
            return list;
        }

        for (int i = 1; i < list.size(); i++) {
            final T key = list.get(i);
            int j = i - 1;

            // сдвигаем элементы, которые больше key
            while (j >= 0 && greater(list.get(j), key, comparator)) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        return list;
    }
}

