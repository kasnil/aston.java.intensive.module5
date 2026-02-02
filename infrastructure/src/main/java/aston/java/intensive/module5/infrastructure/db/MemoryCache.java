package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.User;

public class MemoryCache implements Store {
    private final StoreSet<User> userStoreSet = new UserStoreSet();

    @Override
    public StoreSet<User> getUserStoreSet() {
        return this.userStoreSet;
    }
}
