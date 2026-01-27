package aston.java.intensive.module5.infrastructure.db;

import java.util.List;
import java.util.Optional;
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

    @Override
    public boolean delete(T t) {
        return false;
    }

    @Override
    public void save(T t) {}

    @Override
    public boolean update(T t) {
        return false;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.empty();
    }
}
