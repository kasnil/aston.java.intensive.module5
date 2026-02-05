package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.Identifiable;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.utils.guard.Ensure;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

public abstract class StoreSet<T extends Identifiable<Long>> {
    private final Map<Long,T> table = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    public List<T> findAll() {
        return List.copyOf(table.values());
    }

    public void clear() {
        table.clear();
    }

    public int size() {
        return table.size();
    }

    public boolean delete(T entity) {
       return table.remove(entity.getId()) != null;
    }

    public void save(T entity) {
        Ensure.that(entity).isNotNull();

        if (entity.getId() == null) {
            long id = sequence.getAndIncrement();
            entity.setId(id);
        }

        table.put(entity.getId(), entity);
    }

    public boolean update(T entity) {
        Ensure.that(entity).isNotNull();

        return entity.getId() != null && table.replace(entity.getId(), entity) != null;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(table.get(id));
    }

    public List<T> find(Predicate<T> predicate) {
        return table.values().stream().filter(predicate).toList();
    }

    public boolean isEmpty() {
        return table.isEmpty();
    }

    public void resetSequence() {
        sequence.set(1);
    }
}
