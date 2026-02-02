package aston.java.intensive.module5;

import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.application.filling.FillingStrategyFactory;
import aston.java.intensive.module5.application.filling.strategy.FromFileUserFillingStrategy;
import aston.java.intensive.module5.application.filling.strategy.ManuallyUserFillingStrategy;
import aston.java.intensive.module5.application.filling.strategy.RandomUserFillingStrategy;
import aston.java.intensive.module5.application.serializer.JsonSerializerUserService;
import aston.java.intensive.module5.application.sort.SortStrategyFactory;
import aston.java.intensive.module5.application.sort.strategy.BubbleUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.HeapUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.InsertionUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.MergeUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.OddEvenUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.QuickUserSortStrategy;
import aston.java.intensive.module5.application.sort.strategy.SelectUserSortStrategy;
import aston.java.intensive.module5.infrastructure.db.MemoryCache;
import aston.java.intensive.module5.infrastructure.db.Store;
import aston.java.intensive.module5.infrastructure.db.UnitOfWork;
import aston.java.intensive.module5.infrastructure.db.UserRepository;
import aston.java.intensive.module5.infrastructure.io.ConsoleService;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.presentation.menu.MenuFilling;
import aston.java.intensive.module5.presentation.menu.MenuSort;
import aston.java.intensive.module5.presentation.menu.MenuStart;
import aston.java.intensive.module5.utils.faker.DataLocale;
import aston.java.intensive.module5.utils.faker.DataSet;
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
                    services.addSingleton(JsonSerializerUserService.class);
                    services.addSingleton(DataSet.class, new DataSet(DataLocale.Ru));
                    services.addSingleton(FromFileUserFillingStrategy.class);
                    services.addSingleton(ManuallyUserFillingStrategy.class);
                    services.addSingleton(RandomUserFillingStrategy.class);
                    services.addSingleton(FillingStrategyFactory.class);
                    services.addSingleton(BubbleUserSortStrategy.class);
                    services.addSingleton(HeapUserSortStrategy.class);
                    services.addSingleton(InsertionUserSortStrategy.class);
                    services.addSingleton(MergeUserSortStrategy.class);
                    services.addSingleton(OddEvenUserSortStrategy.class);
                    services.addSingleton(QuickUserSortStrategy.class);
                    services.addSingleton(SelectUserSortStrategy.class);
                    services.addSingleton(SortStrategyFactory.class);
                });
        applicationBuilder.build().run();
    }
}
