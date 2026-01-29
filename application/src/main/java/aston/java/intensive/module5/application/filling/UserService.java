package aston.java.intensive.module5.application.filling;

import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.Repository;

import java.util.List;


public class UserService {

    private final Repository<User> repository;

    public UserService(Repository<User> repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {return this.repository.all();}

    public void fillUsers(int count, FillingStrategy<User> strategy) {
        strategy.fill(count, repository);
    }

}
