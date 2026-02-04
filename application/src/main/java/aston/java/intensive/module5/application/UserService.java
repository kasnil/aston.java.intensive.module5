package aston.java.intensive.module5.application;

import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.UnitOfWork;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.StringUtils;
import aston.java.intensive.module5.utils.sort.ComparatorFactory;
import aston.java.intensive.module5.utils.sort.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

    public void resetUserStore() {
        this.uow.getUserRepository().deleteAll();
        this.uow.getUserRepository().resetSequence();
    }

    public void printUsers(List<User> users) {
        var maxLengths = findMaxLengths(users).get();

        console.output(String.format("╭─%s─┬─%s─┬─%s─╮",
                StringUtils.repeat("─", maxLengths.maxNameLength),
                StringUtils.repeat("─", maxLengths.maxEmailLength),
                StringUtils.repeat("─", maxLengths.maxPasswordLength)));
        console.output(String.format("│ %s │ %s │ %s │",
                StringUtils.center("ИМЯ", maxLengths.maxNameLength, ' '),
                StringUtils.center("EMAIL", maxLengths.maxEmailLength, ' '),
                StringUtils.center("ПАРОЛЬ", maxLengths.maxPasswordLength, ' ')));
        console.output(String.format("├─%s─┼─%s─┼─%s─┤",
                StringUtils.repeat("─", maxLengths.maxNameLength),
                StringUtils.repeat("─", maxLengths.maxEmailLength),
                StringUtils.repeat("─", maxLengths.maxPasswordLength)));
        users.forEach(user -> console.output(String.format("│ %s │ %s │ %s │",
                    StringUtils.center(user.getName(), maxLengths.maxNameLength, ' '),
                    StringUtils.center(user.getEmail(), maxLengths.maxEmailLength, ' '),
                    StringUtils.center(user.getPassword(), maxLengths.maxPasswordLength, ' '))));
        console.output(String.format("╰─%s─┴─%s─┴─%s─╯",
                StringUtils.repeat("─", maxLengths.maxNameLength),
                StringUtils.repeat("─", maxLengths.maxEmailLength),
                StringUtils.repeat("─", maxLengths.maxPasswordLength)));
        var totalMessage = String.format("Всего: %d записей", users.size());
        console.output(StringUtils.right(totalMessage, maxLengths.maxNameLength + maxLengths.maxEmailLength + maxLengths.maxPasswordLength + 10, ' '));
    }

    public static Optional<MaxLengths> findMaxLengths(List<User> users) {
        if (users.isEmpty()) return Optional.of(new MaxLengths());

        try(ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            CompletableFuture<MaxLengths> future = CompletableFuture.supplyAsync(() -> {
                int maxNameLength = Integer.MIN_VALUE;
                int maxEmailLength = Integer.MIN_VALUE;
                int maxPasswordLength = Integer.MIN_VALUE;

                for (User user : users) {
                    maxNameLength = Math.max(maxNameLength, user.getName().length());
                    maxEmailLength = Math.max(maxEmailLength, user.getEmail().length());
                    maxPasswordLength = Math.max(maxPasswordLength, user.getPassword().length());
                }

                return new MaxLengths(maxNameLength, maxEmailLength, maxPasswordLength);
            }, executor);

            return Optional.of(future.get());
        } catch (ExecutionException | InterruptedException e) {
            return Optional.empty();
        }
    }

    record MaxLengths(int maxNameLength, int maxEmailLength, int maxPasswordLength) {
        public MaxLengths() {
            this(0, 0, 0);
        }
    }
}
