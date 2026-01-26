package aston.java.intensive.module5.utils;

import java.util.HashMap;
import java.util.Map;

public final class MapsUtils {
    public static <K extends Object, V extends Object> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K extends Object, V extends Object> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }
}
