package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.Identifiable;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryCache1<T extends Identifiable<Long>> implements Store<T> {
    private final Map<Long,T> table = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);


    @Override
    public List<T> findAll() {
        return List.copyOf(table.values());
    }

    @Override
    public void clear() {
        table.clear();
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public boolean delete(T t) {
       return table.remove(t.getId()) != null;
    }

    @Override
    public void save(T t) {
        Objects.requireNonNull(t);

        if (t.getId() == null) {
            long id = sequence.getAndIncrement();
            t.setId(id);
        }

        table.put(t.getId(), t);
    }

    @Override
    public boolean update(T t) {
        Objects.requireNonNull(t);
        return t.getId() != null && table.replace(t.getId(), t) != null;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(table.get(id));
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    @Override
    public void resetSequence() {
        sequence.set(1);
    }
}
