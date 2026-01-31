package aston.java.intensive.module5.application;

import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.Repository;
import aston.java.intensive.module5.utils.sort.ComparatorFactory;
import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class UserService {

   private static final Comparator<User> DEFAULT_ORDER = Comparator.comparing(User::getId);

    private final Repository<User> repository;

    public UserService(Repository<User> repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {return this.repository.all();}

    public void fillUsers(int count, FillingStrategy<User> strategy) {
        strategy.fill(count, repository);
    }

    public List<User> sortUsers(List<String> byFieldsOrder, SortStrategy<User> sortStrategy) {

        if (sortStrategy == null) {throw new IllegalArgumentException("Стратегия сортировки не задана");}

        if (byFieldsOrder == null || byFieldsOrder.isEmpty()) {throw new IllegalArgumentException("Порядок сортировки отсутсвует");}

        List<User> users = new ArrayList<>(this.repository.all());
        if (users == null || users.isEmpty()) {return List.of();}

        Comparator<User> comparator =
                (byFieldsOrder == null || byFieldsOrder.isEmpty())
                        ? DEFAULT_ORDER
                        : ComparatorFactory.byFields(User.class, byFieldsOrder);

        return sortStrategy.sort(users, comparator);
    }

    public boolean isEmptyStore() {
        return this.repository.isEmpty();
    }

    public void resetUserStore() {
        this.repository.deleteAll();
        this.repository.resetSequence();
    }
}
