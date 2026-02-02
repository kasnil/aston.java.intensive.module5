package aston.java.intensive.module5;

import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.infrastructure.db.MemoryCache;
import aston.java.intensive.module5.infrastructure.db.Store;
import aston.java.intensive.module5.infrastructure.db.UnitOfWork;
import aston.java.intensive.module5.infrastructure.db.UserRepository;
import aston.java.intensive.module5.infrastructure.io.ConsoleService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.presentation.menu.MenuFilling;
import aston.java.intensive.module5.presentation.menu.MenuSort;
import aston.java.intensive.module5.presentation.menu.MenuStart;
import aston.java.intensive.module5.utils.menu.ApplicationBuilder;

public class Main {
    static void main() {
        var applicationBuilder = new ApplicationBuilder(Main.class);
        applicationBuilder
                .addMenu(MenuFilling.class)
                .addMenu(MenuSort.class)
                .addMenu(MenuStart.class)
                .configureServices(services -> {
                    services.addSingleton(Store.class, MemoryCache.class);
                    services.addSingleton(UserRepository.class);
                    services.addSingleton(UnitOfWork.class);
                    services.addSingleton(UserService.class);
                    services.addSingleton(IOService.class, ConsoleService.class);
                });
        applicationBuilder.build().run();
    }
}
