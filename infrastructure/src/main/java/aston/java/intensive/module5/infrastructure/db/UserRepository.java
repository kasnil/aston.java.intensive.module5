package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

public class UserRepository implements Repository<User> {
    private final StoreSet<User> store;

    public UserRepository(Store store) {
        this.store = store.getUserStoreSet();
    }

    @Override
    public List<User> all() {
        return store.findAll();
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override
    public int count() {
        return store.size();
    }

    @Override
    public boolean delete(User user) {
        return store.delete(user);
    }

    @Override
    public void add(User user) {
        store.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return store.findById(id);
    }

    @Override
    public boolean update(User user) {
        return store.update(user);
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public void resetSequence() {
        store.resetSequence();
    }

    @Override
    public int counterN(User entity, ExecutorService executor) {
        return store.counterN(entity, executor);
    }
}
