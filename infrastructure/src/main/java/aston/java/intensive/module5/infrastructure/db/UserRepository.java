package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.User;

import java.util.List;

public class UserRepository implements Repository<User> {
    private final Store<User> store;

    public UserRepository(Store<User> store) {
        this.store = store;
    }

    @Override
    public List<User> all() {
        return store.findAll();
    }
}
