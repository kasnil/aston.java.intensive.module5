package aston.java.intensive.module5.infrastructure.db;


import aston.java.intensive.module5.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Repository<T> {
    List<T> all();

    void deleteAll();

    int count();

    boolean delete(T t);

    void add(T t);

    Optional<T> findById(Long id);

    List<User> find(Predicate<User> predicate);

    boolean update(T t);

    boolean isEmpty();

    void resetSequence();
}