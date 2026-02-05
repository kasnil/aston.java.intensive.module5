package aston.java.intensive.module5.utils.sort;

import aston.java.intensive.module5.utils.sort.cache.SortMetaCache;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class ComparatorFactory {

    public static <T> Comparator<T> byField(Class<T> type, String displayName) {
        SortFieldMeta meta = SortMetaCache.getFields(type).get(displayName);

        if( meta == null) {
            throw new IllegalArgumentException("Поле не найдено: " + displayName);
        }
        return Comparator.comparing(meta::getComparableValue);
    }

    public static <T> Comparator<T> byFields(Class<T> type, List<String> displayNames) {
        if (displayNames == null || displayNames.isEmpty()) {
            throw new IllegalArgumentException("Поля сортировки не заданы");
        }

        Iterator<String> it = displayNames.iterator();
        Comparator<T> comparator = byField(type, it.next());

        while (it.hasNext()) {
            comparator = comparator.thenComparing(byField(type, it.next()));
        }

        return comparator;
    }


    private ComparatorFactory() {}
}
