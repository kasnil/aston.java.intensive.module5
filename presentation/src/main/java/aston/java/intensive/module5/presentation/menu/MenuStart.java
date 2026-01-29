package aston.java.intensive.module5.presentation.menu;

import aston.java.intensive.module5.infrastructure.io.ConsoleService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;

@Menu("index")
public final class MenuStart {
    private final IOService console;

    public MenuStart() {
        this.console = new ConsoleService();
    }

    @Action
    public Response index(Param param) {
        var answer = console.readInt("""
                Select:
                1 - Greet (en)
                2 - Greet (ru)
                3 - Greet (es)
                4 - Сортировка
                5 - Заполнение
                6 - exit""");
        var resource = switch (answer) {
            case 1 -> new Resource("greet", "en");
            case 2 -> new Resource("greet", "ru");
            case 3 -> new Resource("greet", "es");
            case 4 -> new Resource("sort", "chooseSortOrder");
            case 5 -> new Resource("filling", "choiceCount");
            case 6 -> Resource.exit();
            default -> new Resource("index", "not-found");
        };
        return new Response(resource);
    }

    @Action("not-found")
    public Response notFound(Param param) {
        console.output("Not found item menu. Redirect to main menu");

        return new Response(new Resource("index"));
    }
}
