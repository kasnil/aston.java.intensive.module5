package aston.java.intensive.module5.presentation.menu;

import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.presentation.menu.view.MenuItem;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Menu("index")
public final class MenuStart {
    private final IOService console;
    private final UserService userService;

    public MenuStart(
            UserService userService,
            IOService console
    ) {
        this.userService = userService;
        this.console = console;
    }

    @Action
    public Response index(Param param) {

        List<MenuItem> items = new ArrayList<>();
        boolean hasUsers = !userService.isEmptyStore();
        int choice;
        Resource resource;

        // Доступны только если есть пользователи
        items.add(new MenuItem("Сортировка", new Resource("sort", "chooseSortOrder"),() -> hasUsers));
        items.add(new MenuItem("Перезаписать пользователей", new Resource("filling", "overwrite"), () -> hasUsers));
        items.add(new MenuItem("Добавить пользователей", new Resource("filling", "choiceCount"), () -> hasUsers));
        //

        items.add(new MenuItem("Заполнение", new Resource("filling", "choiceCount"), () -> !hasUsers));

        items.add(new MenuItem("Выход", Resource.exit()));

        // Вывод меню
        items = items.stream().filter(MenuItem::enabled).collect(Collectors.toList());
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            console.output((i + 1) + ". " + item.text());
        }

        // Выбор
        while (true) {
            choice = console.readInt("> ") - 1;
            if (choice < 0 || choice >= items.size()) {
                console.output("Неверный выбор, попробуйте ещё раз");
                continue;
            }
            resource = items.get(choice).resource();
            break;
        }

        return new Response(resource);
    }

    @Action("not-found")
    public Response notFound(Param param) {
        console.output("Not found item menu. Redirect to main menu");

        return new Response(new Resource("index"));
    }
}
