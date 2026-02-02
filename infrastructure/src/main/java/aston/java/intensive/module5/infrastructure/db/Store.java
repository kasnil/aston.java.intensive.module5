package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.User;

public interface Store {
    StoreSet<User> getUserSet();
}
