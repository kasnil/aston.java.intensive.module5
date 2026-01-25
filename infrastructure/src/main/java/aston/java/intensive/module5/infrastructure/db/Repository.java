package aston.java.intensive.module5.infrastructure.db;

import java.util.List;

public interface Repository<T> {
    List<T> all();
}