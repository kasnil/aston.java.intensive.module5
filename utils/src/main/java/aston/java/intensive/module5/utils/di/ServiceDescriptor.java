package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.guard.Ensure;

public record ServiceDescriptor<T, TImplementation extends T>(Class<T> serviceClass, Class<TImplementation> theImplementationClass, TImplementation implementationInstance) {
    public ServiceDescriptor {
        Ensure.that(serviceClass).isNotNull();
        Ensure.that(theImplementationClass).isNotNull();
    }

    public ServiceDescriptor(Class<T> serviceClass, Class<TImplementation> theImplementationClass) {
        this(serviceClass, theImplementationClass, null);
    }

    public ServiceDescriptor(Class<T> serviceClass, TImplementation implementationInstance) {
        this(serviceClass, (Class<TImplementation>)implementationInstance.getClass(), implementationInstance);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceClass.getSimpleName() + " = " + serviceClass);
        sb.append(", ");
        sb.append(theImplementationClass.getSimpleName() + " = " + theImplementationClass);
        return sb.toString();
    }
}