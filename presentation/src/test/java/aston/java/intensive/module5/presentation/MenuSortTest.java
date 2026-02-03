package aston.java.intensive.module5.presentation;

import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.application.filling.strategy.RandomUserFillingStrategy;
import aston.java.intensive.module5.application.sort.SortStrategyFactory;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.MemoryCache;
import aston.java.intensive.module5.infrastructure.db.Store;
import aston.java.intensive.module5.infrastructure.db.UnitOfWork;
import aston.java.intensive.module5.infrastructure.db.UserRepository;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.presentation.menu.MenuSort;
import aston.java.intensive.module5.utils.di.ServiceLocator;
import aston.java.intensive.module5.utils.di.ServiceProviderImpl;
import aston.java.intensive.module5.utils.faker.DataLocale;
import aston.java.intensive.module5.utils.faker.DataSet;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;
import aston.java.intensive.module5.utils.sort.strategy.QuickSortStrategy;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuSortTest {

    @Test
    public void testSort_NoSelectedFields() {
        var serviceLocator = getBaseServiceLocator();

        serviceLocator.addSingleton(IOService.class, new IOService() {
            @Override public int readInt() { return 0; }
            @Override public int readIntOrDefault(int defaultValue) { return 0; }
            @Override public int readInt(String prompt) { return 0; }
            @Override public int readIntOrDefault(String prompt, int defaultValue) { return 0; }
            @Override public String readString() { return ""; }
            @Override public String readString(String prompt) { return ""; }
            @Override public void output(Object message) { }
        });

        var serviceProvider = new ServiceProviderImpl.Builder()
                .setServiceCollection(serviceLocator.getServices())
                .build();

        MenuSort menuSort = assertDoesNotThrow(() -> serviceProvider.getService(MenuSort.class).orElseThrow());

        Param param = Param.empty();
        Response response = menuSort.sort(param);

        assertEquals(new Response(new Resource("index")), response);
    }

    @Test
    public void testSort_selectedFields() {
        var serviceLocator = getBaseServiceLocator();
        AtomicInteger calls = new AtomicInteger();

        serviceLocator.addSingleton(IOService.class, new IOService() {
            @Override public int readInt() {
                calls.incrementAndGet();
                return 2;
            }
            @Override public int readIntOrDefault(int defaultValue) { return 2; }
            @Override public int readInt(String prompt) {
                calls.incrementAndGet();
                return 2;
            }
            @Override public int readIntOrDefault(String prompt, int defaultValue) { return 2; }
            @Override public String readString() { return ""; }
            @Override public String readString(String prompt) { return ""; }
            @Override public void output(Object message) { }
        });

        var serviceProvider = new ServiceProviderImpl.Builder()
                .setServiceCollection(serviceLocator.getServices())
                .build();

        UserService userService = serviceProvider.getService(UserService.class).orElseThrow();
        FillingStrategy<User> fillingStrategy =
                serviceProvider.getService(RandomUserFillingStrategy.class).orElseThrow();

        MenuSort menuSort = assertDoesNotThrow(() -> serviceProvider.getService(MenuSort.class).orElseThrow());

        userService.fillUsers(4, fillingStrategy);

        // поле сортировки (Если поле не соответствуе User или нет аннотации @SortUser над полем то тест упадет)
        String selected = "имя,пароль,email";
        Param param = new Param(selected);
        Response response = menuSort.sort(param);

        // если выпала ошибка до вызова второго меню то будет "0"
        assertEquals(1, calls.get());
        assertEquals(new Response(new Resource("index")), response);
    }

    private ServiceLocator getBaseServiceLocator() {
        var serviceLocator = new ServiceLocator();
        serviceLocator.addSingleton(Store.class, MemoryCache.class);
        serviceLocator.addSingleton(UserRepository.class);
        serviceLocator.addSingleton(UnitOfWork.class);
        serviceLocator.addSingleton(UserService.class);

        serviceLocator.addSingleton(DataSet.class, new DataSet(DataLocale.Ru));
        serviceLocator.addSingleton(RandomUserFillingStrategy.class);

        serviceLocator.addSingleton(SortStrategyFactory.class);
        serviceLocator.addSingleton(QuickSortStrategy.class);

        serviceLocator.addSingleton(MenuSort.class);

        return serviceLocator;
    }
}
