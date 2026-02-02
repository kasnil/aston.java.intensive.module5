package aston.java.intensive.module5.application.filling.strategy;

import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.application.filling.exception.UserAbortException;
import aston.java.intensive.module5.application.serializer.JsonSerializerUserService;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.Repository;
import aston.java.intensive.module5.infrastructure.io.ConsoleService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.serializer.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FromFileFillingStrategy implements FillingStrategy<User> {

    private final IOService console;
    private final JsonSerializerUserService serializer;
    // задаётся динамически
    private Path filePath;

    public FromFileFillingStrategy(IOService console, JsonSerializerUserService serializer) {
        this.console = console;
        this.serializer = serializer;
    }
    public FromFileFillingStrategy() {
        this(new ConsoleService(),new JsonSerializerUserService());
    }

    @Override
    public void fill(int count, Repository<User> repository) {
        while (true) {
            try {
                Path path = requestFilePath();
                String json = readFile(path);
                List<User> users = deserializeAndValidateUsers(json, count);
                users.forEach(repository::add);
                return;
            } catch (UserAbortException e) {
                throw e; // возврат в меню
            } catch (IllegalStateException e) {
                console.output(e.getMessage());
            }
        }
    }

    private Path requestFilePath() {
        while (true) {
            console.output("0 -> меню");
            console.output("Введите путь к JSON-файлу с пользователями: ");

            String input = console.readString("< ");

            if (input == null || input.isBlank()) {
                console.output("Путь не может быть пустым.");
                continue;
            }

            if ("0".equals(input.trim())) {
                throw new UserAbortException("Загрузка из файла отменена пользователем");
            }

            Path path = Path.of(input.trim());
            if (Files.exists(path)) {
                return path;
            }

            console.output("Файл не найден. Попробуйте ещё раз.");
        }
    }

    private String readFile(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка чтения файла", e);
        }
    }

    private List<User> deserializeAndValidateUsers(String jsonText, int count) {
        Collection<User> users;
        try {
            users = serializer.deserializeCollection(jsonText).orElseThrow();
        } catch (RuntimeException e) {
            throw new IllegalStateException( "Ошибка в файле: " + e.getMessage(), e );
        }

        if (users.size() < count) {
            throw new IllegalStateException("Недостаточно пользователей в файле. Всего в файле:  " + users.size() + " пользователей.");
        }

        return new ArrayList<>(users).subList(0, count);
    }
}
