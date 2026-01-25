package aston.java.intensive.module5.infrastructure.db;

import java.util.List;

public interface Store<T> {
    List<T> findAll();
    void clear();
    int size();
}
