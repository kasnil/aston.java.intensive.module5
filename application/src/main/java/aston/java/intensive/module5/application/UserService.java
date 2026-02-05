package aston.java.intensive.module5.application;

import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.UnitOfWork;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.StringUtils;
import aston.java.intensive.module5.utils.sort.ComparatorFactory;
import aston.java.intensive.module5.utils.sort.SortFieldMeta;
import aston.java.intensive.module5.utils.sort.SortStrategy;
import aston.java.intensive.module5.utils.sort.cache.SortMetaCache;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;


public class UserService {

   private static final Comparator<User> DEFAULT_ORDER = Comparator.comparing(User::getId);

    private final UnitOfWork uow;
    private final IOService console;

    public UserService(
            UnitOfWork uow,
            IOService console) {
        this.uow = uow;
        this.console = console;
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

    public int getCount() {
        return this.uow.getUserRepository().count();
    }

    public List<User> find() {
        List<String> available = new ArrayList<>(SortMetaCache.getFields(User.class).keySet());
        available.sort(Comparator.naturalOrder());

        Predicate<User> combinedFilter = createFilterForField("", "");
        for (String displayName : available) {
            var value = console.readString("Введите значение для фильтра по полю '" + displayName + "' (оставьте пустым, если фильтр не нужен): ");
            combinedFilter = combinedFilter.and(createFilterForField(displayName, value));
        }
        return this.uow.getUserRepository().find(combinedFilter);
    }

    public void resetUserStore() {
        this.uow.getUserRepository().deleteAll();
        this.uow.getUserRepository().resetSequence();
    }

    private Predicate<User> createFilterForField(String displayName, String value) {
        if (StringUtils.isNullOrEmpty(displayName) || StringUtils.isNullOrEmpty(value)) {
            return user -> true;
        }

        String findValue = value.toLowerCase();
        SortFieldMeta meta = SortMetaCache.getFields(User.class).get(displayName);
        return user -> ((String)(meta.getValue(user))).toLowerCase().contains(findValue);
    }
}
