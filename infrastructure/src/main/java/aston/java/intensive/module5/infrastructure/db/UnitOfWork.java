package aston.java.intensive.module5.infrastructure.db;

public class UnitOfWork {
    private final UserRepository userRepository;

    public UnitOfWork(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }
}
