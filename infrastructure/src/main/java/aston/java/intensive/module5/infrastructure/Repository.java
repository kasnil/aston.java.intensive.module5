package aston.java.intensive.module5.infrastructure;

import java.util.List;

public interface Repository<T> {
    List<T> all();
}