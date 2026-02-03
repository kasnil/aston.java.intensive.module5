package aston.java.intensive.module5.presentation;

import aston.java.intensive.module5.application.UserService;
import aston.java.intensive.module5.application.filling.FillingStrategy;
import aston.java.intensive.module5.application.filling.FillingStrategyFactory;
import aston.java.intensive.module5.application.filling.strategy.RandomUserFillingStrategy;
import aston.java.intensive.module5.domain.User;
import aston.java.intensive.module5.infrastructure.db.MemoryCache;
import aston.java.intensive.module5.infrastructure.db.Store;
import aston.java.intensive.module5.infrastructure.db.UnitOfWork;
import aston.java.intensive.module5.infrastructure.db.UserRepository;
import aston.java.intensive.module5.infrastructure.io.IOService;
import aston.java.intensive.module5.presentation.menu.MenuFilling;
import aston.java.intensive.module5.presentation.menu.MenuStart;
import aston.java.intensive.module5.utils.di.ServiceLocator;
import aston.java.intensive.module5.utils.di.ServiceProviderImpl;
import aston.java.intensive.module5.utils.faker.DataLocale;
import aston.java.intensive.module5.utils.faker.DataSet;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuFillingTest {
    @Test
    public void testFillRandomAction() {
        AtomicInteger counter = new AtomicInteger(0);
        var countUsers = 2;

        var serviceLocator = getBaseServiceLocator();
        serviceLocator.addSingleton(IOService.class, new IOService() {
            @Override
            public int readInt() {
                return 0;
            }

            @Override
            public int readIntOrDefault(int defaultValue) {
                return 0;
            }

            @Override
            public int readInt(String prompt) {
                return 0;
            }

            @Override
            public int readIntOrDefault(String prompt, int defaultValue) {
                return 0;
            }

            @Override
            public String readString() {
                return "";
            }

            @Override
            public String readString(String prompt) {
                return "";
            }

            @Override
            public void output(Object message) {
                if (message.toString().startsWith("User")) {
                    counter.incrementAndGet();
                }
            }
        });

        var serviceProvider = new ServiceProviderImpl.Builder()
                .setServiceCollection(serviceLocator.getServices())
                .build();

        UserService userService = assertDoesNotThrow(() -> serviceProvider.getService(UserService.class).orElseThrow());
        MenuFilling menu = assertDoesNotThrow(() -> serviceProvider.getService(MenuFilling.class).orElseThrow());

        Param param = new Param(countUsers);
        var response = menu.fillRandom(param);

        assertEquals(countUsers, counter.get());
        assertEquals(countUsers, userService.getAllUsers().size());
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
        serviceLocator.addSingleton(FillingStrategyFactory.class);
        serviceLocator.addSingleton(MenuFilling.class);

        return serviceLocator;
    }
}
