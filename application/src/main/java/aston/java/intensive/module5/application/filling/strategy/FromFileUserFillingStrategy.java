package aston.java.intensive.module5.application.filling.strategy;

import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.application.filling.exception.UserAbortException;
import aston.java.intensive.module5.application.serializer.JsonSerializerUserService;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.Repository;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.serializer.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FromFileUserFillingStrategy implements FillingStrategy<User> {

    private final IOService console;
    private final JsonSerializerUserService serializer;
    // задаётся динамически
    private Path filePath;

    public FromFileUserFillingStrategy(IOService console, JsonSerializerUserService serializer) {
        this.console = console;
        this.serializer = serializer;
    }

    @Override
    public void fill(int count, Repository<User> repository) {
        while (true) {
            try {
                Path path = requestFilePath();
                String json = readFile(path);
                List<User> users = parseUsers(json, count);
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

    private List<User> parseUsers(String jsonText, int count) {

        JsonParser parser = new JsonParser();
        JsonValue parsed = parser.parse(jsonText);

        if (!(parsed instanceof JsonObject root)) {
            throw new IllegalStateException("Ожидался JsonObject");
        }

        JsonArray users = root.get("users")
                .filter(v -> v instanceof JsonArray)
                .map(v -> (JsonArray) v)
                .orElseThrow(() -> new IllegalStateException("Список 'users' не найден"));

        if (users.size() < count) {
            throw new IllegalStateException("Недостаточно пользователей в файле");
        }

        List<User> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            JsonObject userJson = (JsonObject) users.value().get(i);
            try {
                result.add(serializer.jsonToUser(userJson));
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException(
                        "Ошибка в пользователе №" + (i + 1) + ": " +
                                (e.getCause() != null ? e.getCause().getMessage() : e.getMessage()),
                        e
                );
            }
        }

        return result;
    }
}
