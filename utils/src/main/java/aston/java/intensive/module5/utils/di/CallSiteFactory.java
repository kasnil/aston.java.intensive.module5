package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.ArrayUtils;
import aston.java.intensive.module5.utils.ReflectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CallSiteFactory {
    private final List<ServiceDescriptor> descriptors;
    private final Map<Class<?>, ServiceDescriptorCacheItem> descriptorLookup;
    private final Map<CallSiteCacheKey, CallSite> callSiteCache;

    public CallSiteFactory(List<ServiceDescriptor> serviceDescriptors) {
        this.descriptors = List.copyOf(serviceDescriptors);
        this.descriptorLookup = new HashMap<>();
        this.callSiteCache = new HashMap<>();

        populate();
    }

    private void populate() {
        for (ServiceDescriptor descriptor : this.descriptors) {
            var serviceClass = descriptor.serviceClass();
            var implementationClass = descriptor.theImplementationClass();
            ServiceDescriptorCacheItem cacheItem = new ServiceDescriptorCacheItem(descriptor);
            this.descriptorLookup.put(serviceClass, cacheItem);
        }
    }

    public Optional<CallSite> getCallSite(ServiceDescriptor serviceDescriptor)
    {
        if (!this.descriptorLookup.containsKey(serviceDescriptor.serviceClass())) {
            return Optional.empty();
        }

        return tryCreateExact(serviceDescriptor);
    }

    public Optional<CallSite> getCallSite(Class<?> serviceClass) {
        CallSiteCacheKey callSiteKey = new CallSiteCacheKey(serviceClass);
        if (this.callSiteCache.containsKey(callSiteKey)) {
            return Optional.of(callSiteCache.get(callSiteKey));
        }
        return createCallSite(serviceClass);
    }

    private Optional<CallSite> createCallSite(Class<?> serviceClass) {
        return tryCreateExact(serviceClass);
    }

    private Optional<CallSite> tryCreateExact(Class<?> serviceClass)
    {
        if (this.descriptorLookup.containsKey(serviceClass))
        {
            ServiceDescriptorCacheItem descriptor = this.descriptorLookup.get(serviceClass);
            return tryCreateExact(descriptor.item());
        }

        return Optional.empty();
    }

    private Optional<CallSite> tryCreateExact(ServiceDescriptor serviceDescriptor) {
        CallSite callSite = null;
        CallSiteCacheKey callSiteKey = new CallSiteCacheKey(serviceDescriptor.serviceClass());

        if (serviceDescriptor.theImplementationClass() != null) {
            if (this.callSiteCache.containsKey(callSiteKey)) {
                return Optional.of(callSiteCache.get(callSiteKey));
            }

            if (serviceDescriptor.implementationInstance() != null) {
                callSite = new ValueCallSite(serviceDescriptor.serviceClass(), serviceDescriptor.implementationInstance());
            }
            else {
                callSite = createConstructorCallSite(serviceDescriptor.serviceClass(), serviceDescriptor.theImplementationClass());
            }
        }

        if (callSite != null) {
            this.callSiteCache.put(callSiteKey, callSite);
            return Optional.of(callSite);
        }

        return Optional.empty();
    }

    private CallSite createConstructorCallSite(Class<?> serviceClass, Class<?> implementationClass) {
        var constructors = implementationClass.getConstructors();

        if (constructors.length == 0) {
            Constructor<?> constructor = ReflectUtils.getConstructor(implementationClass, ArrayUtils.newArray(serviceClass.getClass(), 0));
            return new ConstructorCallSite(serviceClass, constructor);
        } else if (constructors.length == 1) {
            Constructor<?> constructor = constructors[0];
            Parameter[] parameters = constructor.getParameters();
            if (parameters.length == 0)
            {
                return new ConstructorCallSite(serviceClass, constructor);
            }

            CallSite[] parameterCallSites = createArgumentCallSites(
                    implementationClass,
                    parameters);

            return new ConstructorCallSite(serviceClass, constructor, parameterCallSites);
        }

        return null;
    }

    private CallSite[] createArgumentCallSites(
            Class<?> implementationType,
            Parameter[] parameters) {
        var parameterCallSites = new CallSite[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            var parameterType = parameters[i].getType();
            Optional<CallSite> callSite = getCallSite(parameterType);

            if (callSite.isEmpty())
            {
                throw new IllegalArgumentException(String.format("Не удалось разрешить службу для типа '{0}' при попытке активировать '{1}'.", parameterType.getName(), implementationType.getName()));
            }

            parameterCallSites[i] = callSite.get();
        }

        return parameterCallSites;
    }

    record CallSiteCacheKey(Class<?> serviceClass) { }

    record ServiceDescriptorCacheItem(ServiceDescriptor item) { }
}
