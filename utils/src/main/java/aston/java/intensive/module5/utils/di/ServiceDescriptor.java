package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.guard.Ensure;

public record ServiceDescriptor<T, TImplementation extends T>(Class<T> serviceClass, Class<TImplementation> theImplementation) {
    public ServiceDescriptor {
        Ensure.that(serviceClass).isNotNull();
        Ensure.that(theImplementation).isNotNull();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceClass.getSimpleName() + " = " + serviceClass);
        sb.append(", ");
        sb.append(theImplementation.getSimpleName() + " = " + theImplementation);
        return sb.toString();
    }
}