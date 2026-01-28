package aston.java.intensive.module5.presentation.menu;

import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.io.ConsoleService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;
import aston.java.intensive.module5.utils.sort.cache.SortMetaCache;

import java.util.ArrayList;
import java.util.List;


@Menu("sort")
public final class MenuSort {
    private final IOService console;

    public MenuSort() {
        this.console = new ConsoleService();
    }

    @Action("chooseSortOrder")
    public Response chooseSortOrder(Param param) {

        List<String> selected = parseSelected(param);

        List<String> available = new ArrayList<>(SortMetaCache.getFields(User.class).keySet());
        available.removeAll(selected);

        // если всё выбрали
        if (available.isEmpty()) {
            console.output("Порядок сортировки: " + selected);
            return new Response(new Resource("index"));
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
            return new Response(new Resource("index"));
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

    private List<String> parseSelected(Param param) {
        if (param == null || param.message() == null || param.message().isBlank()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(List.of(param.message().split(",")));
    }

    private Param toParam(List<String> selected) {
        return selected.isEmpty()
                ? Param.empty()
                : new Param(String.join(",", selected));
    }
}
