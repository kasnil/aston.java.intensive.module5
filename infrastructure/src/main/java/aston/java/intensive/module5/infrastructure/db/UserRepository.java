package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class UserRepository implements Repository<User> {
    private final StoreSet<User> set;

    public UserRepository(Store store) {
        this.set = store.getUserStoreSet();
    }

    @Override
    public List<User> all() {
        return set.findAll();
    }

    @Override
    public void deleteAll() {
        set.clear();
    }

    @Override
    public int count() {
        return set.size();
    }

    @Override
    public boolean delete(User user) {
        return set.delete(user);
    }

    @Override
    public void add(User user) {
        set.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return set.findById(id);
    }

    @Override
    public List<User> find(Predicate<User> predicate) {
        return set.find(predicate);
    }

    @Override
    public boolean update(User user) {
        return set.update(user);
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public void resetSequence() {
        set.resetSequence();
    }
}
