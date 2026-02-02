package aston.java.intensive.module5.infrastructure.db;

import aston.java.intensive.module5.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryCache implements Store {
    private final Table<User> userTable = new UserTable();

    @Override
    public Table<User> getUserTable() {
        return this.userTable;
    }
}
