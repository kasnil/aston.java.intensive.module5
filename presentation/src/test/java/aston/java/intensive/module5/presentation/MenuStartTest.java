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
import aston.java.intensive.module5.presentation.menu.MenuStart;
import aston.java.intensive.module5.utils.di.ServiceLocator;
import aston.java.intensive.module5.utils.di.ServiceProviderImpl;
import aston.java.intensive.module5.utils.faker.DataLocale;
import aston.java.intensive.module5.utils.faker.DataSet;
import aston.java.intensive.module5.utils.menu.models.Param;
import aston.java.intensive.module5.utils.menu.models.Resource;
import aston.java.intensive.module5.utils.menu.models.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuStartTest {
    @Test
    public void testIndexActionSelectSort() {
        var serviceLocator = getBaseServiceLocator();
        serviceLocator.addSingleton(IOService.class, new IOService() {
            @Override
            public int readInt() {
                return 0;
            }

            @Override
            public int readInt(String prompt) {
                return 1;
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
            }
        });

        var serviceProvider = new ServiceProviderImpl.Builder()
                .setServiceCollection(serviceLocator.getServices())
                .build();

        UserService userService = assertDoesNotThrow(() -> serviceProvider.getService(UserService.class).orElseThrow());
        FillingStrategy<User> randomUserFillingStrategy = assertDoesNotThrow(() -> serviceProvider.getService(RandomUserFillingStrategy.class).orElseThrow());
        userService.fillUsers(10, randomUserFillingStrategy);

        MenuStart menu = assertDoesNotThrow(() -> serviceProvider.getService(MenuStart.class).orElseThrow());
        Param param = Param.empty();
        var response = menu.index(param);
        assertEquals(new Response(new Resource("sort", "chooseSortOrder"), param), response);
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
        serviceLocator.addSingleton(MenuStart.class);

        return serviceLocator;
    }
}
