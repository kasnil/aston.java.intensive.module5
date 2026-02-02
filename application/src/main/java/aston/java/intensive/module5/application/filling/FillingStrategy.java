package aston.java.intensive.module5.application.filling;


import aston.java.intensive.module5.infrastructure.db.Repository;

public interface FillingStrategy<T> {
    void fill(int count, Repository<T> repository);
}
