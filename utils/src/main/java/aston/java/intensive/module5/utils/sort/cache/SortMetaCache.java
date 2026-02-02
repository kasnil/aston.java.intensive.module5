package aston.java.intensive.module5.utils.sort.cache;


import aston.java.intensive.module5.utils.sort.SortFieldMeta;
import aston.java.intensive.module5.utils.sort.annotation.SortField;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public final class SortMetaCache {

    private static final Map<Class<?>, Map<String, SortFieldMeta>> CACHE = new HashMap<>();

    public static Map<String, SortFieldMeta> getFields(Class<?> type) {
        return CACHE.computeIfAbsent(type, SortMetaCache::scan);
    }

    private static Map<String, SortFieldMeta> scan(Class<?> type) {
        Map<String, SortFieldMeta> result = new LinkedHashMap<>();

        for (Field field : type.getDeclaredFields()) {
            SortField annotation = field.getAnnotation(SortField.class);
            if (annotation == null) continue;

            String fieldName = field.getName();
            String getterName =
                    "get" + Character.toUpperCase(fieldName.charAt(0))
                            + fieldName.substring(1);

            try {
                Method getter = type.getMethod(getterName);

                String displayName = annotation.displayName();
                if (result.containsKey(displayName)) {
                    throw new IllegalStateException("Дублирующий displayName: " + displayName);
                }

                result.put(
                        displayName,
                        new SortFieldMeta(field, getter, displayName)
                );

            } catch (NoSuchMethodException e) {
                throw new RuntimeException(
                        "Нет getter-а для поля " + fieldName +
                                " в " + type.getSimpleName()
                );
            }
        }

        return Map.copyOf(result);
    }
}
