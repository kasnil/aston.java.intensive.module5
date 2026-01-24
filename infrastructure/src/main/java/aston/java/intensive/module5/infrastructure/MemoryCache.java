package aston.java.intensive.module5.infrastructure;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryCache<T> implements Store<T> {
    private final List<T> cache = new CopyOnWriteArrayList<>();

    public List<T> findAll() {
        return cache;
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }
}
