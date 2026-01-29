package aston.java.intensive.module5.infrastructure.db;

import java.util.List;
import java.util.Optional;

public interface Store<T> {
    List<T> findAll();

    void clear();

    int size();

    boolean delete(T t);

    void save(T t);

    boolean update(T t);

    Optional<T> findById(Long id);

    boolean isEmpty();

    void resetSequence();
}
