package aston.java.intensive.module5.presentation.menu;

import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.application.serializer.CsvSerializerUserService;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Menu("additional-tasks")
public final class MenuAdditionalTasks {
    private final IOService console;
    private final UserService userService;
    private final CsvSerializerUserService csvSerializerUserService;

    public MenuAdditionalTasks(
            UserService userService,
            IOService console,
            CsvSerializerUserService csvSerializerUserService
    ) {
        this.userService = userService;
        this.console = console;
        this.csvSerializerUserService = csvSerializerUserService;
    }

    @Action
    public Response index(Param param) {
        var answer = console.readIntOrDefault("""
                Select:
                1 - Дополнительное задание 1
                3 - Дополнительное задание 3
                4 - Дополнительное задание 4
                5 - Главное меню""", -1);
        var resource = switch (answer) {
            case 1 -> new Resource("additional-tasks", "1");
            case 3 -> new Resource("additional-tasks", "3");
            case 4 -> new Resource("additional-tasks", "4");
            case 5 -> new Resource("index");
            default -> new Resource("additional-tasks", "not-found");
        };
        return new Response(resource);
    }

    @Action("1")
    public Response task1(Param param) {
        console.output("В процессе реализации");
        return new Response(new Resource("additional-tasks"));
    }

    @Action("2")
    public Response task2(Param param) {
        String input = console.readString("Введите путь к CSV-файлу с пользователями: ");

        Path path = Path.of(input.trim());
        List<User> users = (List<User>)(param.data());

        boolean append = false;
        try {
            if (Files.exists(path) && Files.size(path) > 0) {
                append = true;
            }
        } catch (IOException e) {
            console.output(String.format("Ошибка получении информации по файлу '%s': %s", path, e.getMessage()));
        }
        var serializedResult = this.csvSerializerUserService.serializeCollection(users);
        if (serializedResult.isErr()) {
            console.output(String.format("Ошибка сериализации: %s.", serializedResult.getCause().getMessage()));
        }
        else {
            try (
                    FileOutputStream fos = new FileOutputStream(path.toFile(), append);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8))) {
                if (!append) {
                    writer.write(serializedResult.getValue());
                } else {
                    for (String line : serializedResult.getValue().lines().skip(1).toList()) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } catch (Exception e) {
                console.output(String.format("Ошибка при записи файла: %s", e.getMessage()));
            }
        }

        console.output("Возврат в основное меню");
        return new Response(new Resource("index"));
    }

    @Action("3")
    public Response task3(Param param) {
        console.output("В процессе реализации");
        return new Response(new Resource("additional-tasks"));
    }

    @Action("4")
    public Response task4(Param param) {
        console.output("В процессе реализации");
        return new Response(new Resource("additional-tasks"));
    }

    @Action("not-found")
    public Response notFound(Param param) {
        console.output("Некорректный выбор");

        return new Response(new Resource("additional-tasks"));
    }
}
