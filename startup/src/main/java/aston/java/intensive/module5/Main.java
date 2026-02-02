package aston.java.intensive.module5;
import aston.java.intensive.module5.application.AppContext;
import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.MemoryCache1;
import aston.java.intensive.module5.infrastructure.db.Repository;
import aston.java.intensive.module5.infrastructure.db.Store;
import aston.java.intensive.module5.infrastructure.db.UserRepository;
import aston.java.intensive.module5.presentation.menu.MenuFilling;
import aston.java.intensive.module5.presentation.menu.MenuGreet;
import aston.java.intensive.module5.presentation.menu.MenuSort;
import aston.java.intensive.module5.presentation.menu.MenuStart;
import aston.java.intensive.module5.utils.menu.ApplicationBuilder;

public class Main {
    static void main() {
         Store<User> store = new MemoryCache1<>();
         Repository<User> userRepository = new UserRepository(store);
         UserService userService = new UserService(userRepository);

        AppContext.init(userService);

        var applicationBuilder = new ApplicationBuilder(Main.class);
        applicationBuilder
                .addMenu(MenuGreet.class)
                .addMenu(MenuFilling.class)
                .addMenu(MenuSort.class)
                .addMenu(MenuStart.class);
        applicationBuilder.build().run();
    }
}
