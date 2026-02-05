package aston.java.intensive.module5.infrastructure.io;

import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.utils.StringUtils;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsoleService implements IOService {
    private final PrintStream output;
    private final Scanner input;

    public ConsoleService() {
        output = System.out;
        input = new Scanner(System.in);
    }

    @Override
    public int readInt() {
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public int readInt(String prompt) {
        output(prompt);
        return readInt();
    }

    @Override
    public String readString() {return input.nextLine();}

    @Override
    public String readString(String prompt) {
        output(prompt);
        return readString();
    }

    @Override
    public void output(Object message) {
        output.println(message);
    }

    @Override
    public void outputTable(List<User> users) {
        var totalMessage = String.format("Всего: %d записей", users.size());
        if (!users.isEmpty()) {
            var maxLengths = findMaxLengths(users).get();

            output(String.format("╭─%s─┬─%s─┬─%s─╮",
                    StringUtils.repeat("─", maxLengths.maxNameLength),
                    StringUtils.repeat("─", maxLengths.maxEmailLength),
                    StringUtils.repeat("─", maxLengths.maxPasswordLength)));
            output(String.format("│ %s │ %s │ %s │",
                    StringUtils.center("ИМЯ", maxLengths.maxNameLength, ' '),
                    StringUtils.center("EMAIL", maxLengths.maxEmailLength, ' '),
                    StringUtils.center("ПАРОЛЬ", maxLengths.maxPasswordLength, ' ')));
            output(String.format("├─%s─┼─%s─┼─%s─┤",
                    StringUtils.repeat("─", maxLengths.maxNameLength),
                    StringUtils.repeat("─", maxLengths.maxEmailLength),
                    StringUtils.repeat("─", maxLengths.maxPasswordLength)));
            users.forEach(user -> output(String.format("│ %s │ %s │ %s │",
                    StringUtils.center(user.getName(), maxLengths.maxNameLength, ' '),
                    StringUtils.center(user.getEmail(), maxLengths.maxEmailLength, ' '),
                    StringUtils.center(user.getPassword(), maxLengths.maxPasswordLength, ' '))));
            output(String.format("╰─%s─┴─%s─┴─%s─╯",
                    StringUtils.repeat("─", maxLengths.maxNameLength),
                    StringUtils.repeat("─", maxLengths.maxEmailLength),
                    StringUtils.repeat("─", maxLengths.maxPasswordLength)));
            output(StringUtils.right(totalMessage, maxLengths.maxNameLength + maxLengths.maxEmailLength + maxLengths.maxPasswordLength + 10, ' '));
        }
        else {
            output(totalMessage);
        }
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
