package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.List;

public class ServiceLocator {
    private final List<Class<?>> container = ListsUtils.newArrayList();
    private final ServiceCollection services = new ServiceCollection();
    private static ServiceLocator instance;

    private ServiceLocator() {}

    public static synchronized ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public <T> void addSingleton(Class<T> theImplementation) {
        Ensure.that(theImplementation).isNotNull();

        addSingleton(theImplementation, theImplementation);
    }

    public <T, TImplementation extends T> void addSingleton(Class<T> serviceClass, Class<TImplementation> theImplementation) {
        Ensure.that(serviceClass).isNotNull();
        Ensure.that(theImplementation).isNotNull();

        ServiceDescriptor descriptor = new ServiceDescriptor(serviceClass, theImplementation);
        this.services.add(descriptor);
    }

    public List<ServiceDescriptor> getServices() {
        return List.copyOf(services.getServices());
    }
}
