package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.guard.Ensure;

public record ServiceDescriptor<T, TImplementation extends T>(Class<T> serviceClass, Class<TImplementation> theImplementationClass) {
    public ServiceDescriptor {
        Ensure.that(serviceClass).isNotNull();
        Ensure.that(theImplementationClass).isNotNull();
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