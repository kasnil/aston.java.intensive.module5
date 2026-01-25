package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.ListsUtils;

import java.util.List;

public class ServiceLocator {
    private final List<Class<?>> services = ListsUtils.newArrayList();

    private static ServiceLocator instance;

    private ServiceLocator() {}

    public static synchronized ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public <T extends Class<?>> void add(Class<?> theClass) {
        services.add(theClass);
    }

    public List<Class<?>> getServices() {
        return ListsUtils.newArrayList(services);
    }
}
