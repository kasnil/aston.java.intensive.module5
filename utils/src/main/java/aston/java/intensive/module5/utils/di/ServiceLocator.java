package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.guard.Ensure;

import java.util.List;

public class ServiceLocator {
    private final List<Class<?>> container = ListsUtils.newArrayList();
    private final ServiceCollection services = new ServiceCollection();

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

    public <T> void addSingleton(T implementationInstance) {
        Ensure.that(implementationInstance).isNotNull();

        ServiceDescriptor descriptor = new ServiceDescriptor(implementationInstance.getClass(), implementationInstance.getClass(), implementationInstance);
        this.services.add(descriptor);
    }

    public <T, TImplementation extends T>  void addSingleton(Class<T> serviceClass, T implementationInstance) {
        Ensure.that(implementationInstance).isNotNull();

        ServiceDescriptor descriptor = new ServiceDescriptor(serviceClass, implementationInstance.getClass(), implementationInstance);
        this.services.add(descriptor);
    }

    public ServiceCollection getServices() {
        return this.services;
    }
}
