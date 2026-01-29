package aston.java.intensive.module5.presentation.menu.view;

import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.menu.models.Resource;

import java.util.ArrayList;
import java.util.List;

public final class  ViewMenu {

    public static Resource initialView(IOService console, UserService userService) {
        List<MenuItem> items = new ArrayList<>();
        boolean hasUsers = !userService.isEmptyStore();

        items.add(new MenuItem("Greet (en)", new Resource("greet", "en"), true));
        items.add(new MenuItem("Greet (ru)", new Resource("greet", "ru"), true));
        items.add(new MenuItem("Greet (es)", new Resource("greet", "es"), true));

        // Сортировка доступна только если есть пользователи
        items.add(new MenuItem("Сортировка", new Resource("sort", "chooseSortOrder"), hasUsers));

        if (hasUsers) {
            items.add(new MenuItem("Перезаписать пользователей", new Resource("filling", "overwrite"), true));
            items.add(new MenuItem("Добавить пользователей", new Resource("filling", "choiceCount"), true));
        } else {
            items.add(new MenuItem("Заполнение", new Resource("filling", "choiceCount"), true));
        }

        items.add(new MenuItem("Выход", Resource.exit(), true));

        // Вывод меню
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            console.output((i + 1) + ". " + item.text() + (item.enabled() ? "" : " (недоступно)"));
        }

        int choice;
        while (true) {
            choice = console.readInt("> ") - 1;
            if (choice < 0 || choice >= items.size()) {
                console.output("Неверный выбор, попробуйте ещё раз");
                continue;
            }
            MenuItem selected = items.get(choice);
            if (!selected.enabled()) {
                console.output("Эта опция недоступна");
                continue;
            }
            return selected.resource();
        }
    }
}
