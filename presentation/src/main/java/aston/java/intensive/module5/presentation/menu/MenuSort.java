package aston.java.intensive.module5.presentation.menu;

import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.application.sort.SortStrategyFactory;
import aston.java.intensive.module5.application.sort.SortStrategyKind;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.StringUtils;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;
import aston.java.intensive.module5.utils.sort.SortStrategy;
import aston.java.intensive.module5.utils.sort.cache.SortMetaCache;

import java.util.ArrayList;
import java.util.List;


@Menu("sort")
public final class MenuSort {
    private final IOService console;
    private final UserService userService;
    private final SortStrategyFactory<User> sortStrategyFactory;

    public MenuSort(
            UserService userService,
            IOService console,
            SortStrategyFactory<User> sortStrategyFactory
    ) {
        this.userService = userService;
        this.console = console;
        this.sortStrategyFactory = sortStrategyFactory;
    }

    @Action("chooseSortOrder")
    public Response chooseSortOrder(Param param) {

        List<String> selected = parseSelected(param);

        List<String> available = new ArrayList<>(SortMetaCache.getFields(User.class).keySet());
        available.removeAll(selected);

        // если всё выбрали
        if (available.isEmpty()) {
            console.output("Порядок сортировки: " + selected);
            return new Response(
                    new Resource("sort", "sort"),
                    toParam(selected)
            );
        }

        console.output("Выберите поле для сортировки");

        int i = 1;
        for (String field : available) {
            console.output(i++ + ". " + field);
        }
        console.output("0. Завершить выбор");
        console.output("-1. Главное меню");

        int choice = console.readInt("> ");

        // главное меню
        if (choice == -1) {
            return new Response(new Resource("index"));
        }

        // выход
        if (choice == 0) {
            console.output("Порядок сортировки: " + selected);

            return new Response(
                    new Resource("sort", "sort"),
                    toParam(selected)
            );
        }

        // неверный ввод
        if (choice < 1 || choice > available.size()) {
            console.output("Неверный ввод");
            return new Response(
                    new Resource("sort", "chooseSortOrder"),
                    param
            );
        }

        // корректный выбор
        String picked = available.get(choice - 1);
        selected.add(picked);
        console.output("Добавлено: " + picked);

        return new Response(
                new Resource("sort", "chooseSortOrder"),
                toParam(selected)
        );
    }

    @Action("sort")
    public Response sort(Param param) {
        List<String> selected = parseSelected(param);

        if (selected.isEmpty()) {
            console.output("Поля сортировки не выбраны");
            return new Response(new Resource("index"));
        }

        try {
             SortStrategy<User> strategy = sortStrategyFactory.getSortStrategy(SortStrategyKind.Quick);
             List<User> users = userService.sortUsers(selected, strategy);

            console.output("Результат сортировки:");
            users.forEach(console::output);

            var answer = console.readInt("""
                Select:
                1 - Записать результат в файл
                2 - Главное меню""");
            var response = switch (answer) {
                case 1 -> new Response(new Resource("additional-tasks", "2"), new Param(users));
                default -> new Response(new Resource("index"));
            };

            return response;
        } catch (IllegalArgumentException e) {
            console.output("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            console.output("Внутренняя ошибка сортировки");
        }

        return new Response(new Resource("index"));
    }

    private List<String> parseSelected(Param param) {
        var str = (String)(param.data());
        if (StringUtils.isNullOrEmpty(str)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(List.of(str.split(",")));
    }

    private Param toParam(List<String> selected) {
        return selected.isEmpty()
                ? Param.empty()
                : new Param(String.join(",", selected));
    }
}
