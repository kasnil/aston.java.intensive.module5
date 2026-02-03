package aston.java.intensive.module5.application;

import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.UnitOfWork;
import aston.java.intensive.module5.utils.sort.ComparatorFactory;
import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class UserService {

   private static final Comparator<User> DEFAULT_ORDER = Comparator.comparing(User::getId);

    private final UnitOfWork uow;

    public UserService(UnitOfWork uow) {
        this.uow = uow;
    }

    public List<User> getAllUsers() {return this.uow.getUserRepository().all();}

    public void fillUsers(int count, FillingStrategy<User> strategy) {strategy.fill(count, uow.getUserRepository());}

    public List<User> sortUsers(List<String> byFieldsOrder, SortStrategy<User> sortStrategy) {

        if (sortStrategy == null) {throw new IllegalArgumentException("Стратегия сортировки не задана");}

        if (byFieldsOrder == null || byFieldsOrder.isEmpty()) {throw new IllegalArgumentException("Порядок сортировки отсутсвует");}

        List<User> users = new ArrayList<>(this.uow.getUserRepository().all());
        if (users == null || users.isEmpty()) {return List.of();}

        Comparator<User> comparator =
                (byFieldsOrder == null || byFieldsOrder.isEmpty())
                        ? DEFAULT_ORDER
                        : ComparatorFactory.byFields(User.class, byFieldsOrder);

        return sortStrategy.sort(users, comparator);
    }

    public boolean isEmptyStore() {
        return this.uow.getUserRepository().isEmpty();
    }

    public void resetUserStore() {
        this.uow.getUserRepository().deleteAll();
        this.uow.getUserRepository().resetSequence();
    }
}
