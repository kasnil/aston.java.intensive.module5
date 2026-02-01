package aston.java.intensive.module5.utils.di;

import aston.java.intensive.module5.utils.guard.Ensure;

public class ValueCallSite extends CallSite {
    private final Object value;

    public ValueCallSite(Class<?> serviceClass, Object value) {
        Ensure.that(value).isNotNull();

        super(serviceClass);
        this.value = value;;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
