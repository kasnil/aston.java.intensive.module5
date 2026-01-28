package aston.java.intensive.module5.presentation.menu;


import aston.java.intensive.module5.application.filling.UserService;
import aston.java.intensive.module5.application.filling.strategy.ManuallyUserFillingStrategy;
import aston.java.intensive.module5.application.filling.strategy.RandomUserFillingStrategy;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.MemoryCache1;
import aston.java.intensive.module5.infrastructure.db.Repository;
import aston.java.intensive.module5.infrastructure.db.Store;
import aston.java.intensive.module5.infrastructure.db.UserRepository;
import aston.java.intensive.module5.infrastructure.io.ConsoleService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.utils.menu.annotation.Action;
import aston.java.intensive.module5.utils.menu.annotation.Menu;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;


@Menu("filling")
public final class MenuFilling {
    private final IOService console;
    private static final Store<User> store = new MemoryCache1<>();
    private static final Repository<User> userRepository = new UserRepository(store);
    public static final UserService userService = new UserService(userRepository);

    public MenuFilling() {
        this.console = new ConsoleService();
    }

    @Action("choiceCount")
    public Response choiceCount(Param param) {
        console.output("Введите количество пользователей: ");
        var choice = console.readInt("< ");

        if (choice <= 0) {
            console.output("Введите положительное число: ");
            return new Response(new Resource("filling", "choiceCount"));
        }

        return new Response(
                new Resource("filling", "choiceStrategy"),
                new Param(Integer.toString(choice))
        );
    }

    @Action("choiceStrategy")
    public Response choiceStrategy(Param param) {
        console.output("""
                    Выберите способ заполнения:
                    1 - Вручную
                    2 - Рандом
                    3 - Из файла
                    """);
        var choice = console.readInt("< ");
        var answer = switch (choice) {
            case 1 -> new Resource("filling", "fillManually");
            case 2 -> new Resource("filling", "fillRandom");
            case 3 -> new Resource("filling", "fillFromFile");
            default -> {
                console.output("Неверный выбор!");
                yield new Resource("filling", "choiceStrategy");
            }
        };

        return new Response(answer, new Param(param.message()));
    }

    @Action("fillManually")
    public Response fillManually(Param param) {
        console.output("Вручную");
        var count = Integer.parseInt(param.message());

        userService.fillUsers(count, new ManuallyUserFillingStrategy());

        showUsers();

        return new Response(new Resource("index", "index"));
    }

    @Action("fillRandom")
    public Response fillRandom(Param param) {
        console.output("Рандом");
        var count = Integer.parseInt(param.message());

        userService.fillUsers(count, new RandomUserFillingStrategy());

        showUsers();

        return new Response(new Resource("index", "index"));
    }

    @Action("fillFromFile")
    public Response fillFromFile(Param param) {
        console.output("Из файла");

        return new Response(new Resource("index", "index"));
    }

    private void showUsers() {
        console.output("Список пользователей: ");
        for (User user : userService.getAllUsers()) {
            console.output(user.getId() + " " + user);
        }
    }

}
