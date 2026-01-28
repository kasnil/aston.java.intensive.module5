package aston.java.intensive.module5.utils.sort;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class SortFieldMeta {

    private final Field field;
    private final Method getter;
    private final String displayName;

    public SortFieldMeta(Field field, Method getter, String displayName) {
        this.field = field;
        this.getter = getter;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    @SuppressWarnings("unchecked")
    public <T> Comparable<Object> getValue(T instance) {
        try {
            return (Comparable<Object>) getter.invoke(instance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
