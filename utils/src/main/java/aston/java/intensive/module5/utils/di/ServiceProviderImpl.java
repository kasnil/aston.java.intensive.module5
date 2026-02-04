package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.AggregateException;
import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.Result;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ServiceProviderImpl implements ServiceProvider {
    private List<ServiceDescriptor> descriptors;
    private CallSiteFactory callSiteFactory;
    private RuntimeResolver runtimeResolver;

    private void init(ServiceCollection services) {
        this.descriptors = services.getServices();
        this.callSiteFactory = new CallSiteFactory(this.descriptors);
        this.runtimeResolver = new RuntimeResolver();

        List<RuntimeException> exceptions = ListsUtils.newArrayList();
        for (ServiceDescriptor descriptor : this.descriptors) {
            try
            {
                validateService(descriptor);
            }
            catch (RuntimeException e)
            {
                exceptions.add(e);
            }
        }

        if (!exceptions.isEmpty())
        {
            throw new AggregateException(new IllegalStateException("Некоторые сервисы невозможно построить."), exceptions.toArray(new Throwable[0]));
        }
    }

    public List<ServiceDescriptor> getDescriptors() {
        return List.copyOf(descriptors);
    }

    private void validateService(ServiceDescriptor descriptor) {
        try
        {
            this.callSiteFactory.getCallSite(descriptor);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(String.format("Ошибка при проверке дескриптора сервиса '%s': %s", e.getMessage(), e), e);
        }
    }

    public <T> Result<T> getService(Class<T> serviceClass) {
        Supplier<Optional<Object>> realizedService = createServiceAccessor(serviceClass);
        var result = realizedService.get();
        if (result.isEmpty()) {
            return Result.err(new IllegalArgumentException(String.format("Для типа '%s' не зарегистрирована ни один сервис.", serviceClass.getName())));
        }
        return Result.ok((T)result.get());
    }

    private Supplier<Optional<Object>> createServiceAccessor(Class<?> serviceClass)
    {
        var callSite = callSiteFactory.getCallSite(serviceClass);
        if (callSite.isPresent())
        {
            return () -> runtimeResolver.Resolve(callSite.get());
        }

        return () -> Optional.empty();
    }

    public static class Builder {
        private ServiceCollection services;

        public Builder setServiceCollection(
                ServiceCollection services) {
            this.services = services;
            return this;
        }

        public ServiceProvider build() {
            var serviceProvider = new ServiceProviderImpl();
            services.add(new ServiceDescriptor(ServiceProvider.class, serviceProvider));
            serviceProvider.init(services);
            return serviceProvider;
        }
    }
}
