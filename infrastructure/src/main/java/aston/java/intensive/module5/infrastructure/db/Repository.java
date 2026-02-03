package aston.java.intensive.module5.infrastructure.db;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

public interface Repository<T> {
    List<T> all();

    void deleteAll();

    int count();

    boolean delete(T t);

    void add(T t);

    Optional<T> findById(Long id);

    boolean update(T t);

    boolean isEmpty();

    void resetSequence();

    int counterN(T entity, ExecutorService executor);
}