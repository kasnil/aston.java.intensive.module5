package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.User;

public class MemoryCache implements Store {
    private final StoreSet<User> userSet = new UserTable();

    @Override
    public StoreSet<User> getUserSet() {
        return this.userSet;
    }
}
