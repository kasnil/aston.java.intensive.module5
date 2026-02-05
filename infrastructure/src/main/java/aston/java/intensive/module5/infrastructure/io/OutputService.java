package aston.java.intensive.module5.infrastructure.io;

import aston.java.intensive.module5.domain.User;

import java.util.List;

public interface OutputService {
    void output(Object message);

    void outputTable(List<User> users);
}
