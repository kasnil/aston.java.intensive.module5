package aston.java.intensive.module5.utils.di;

public class CallSite {
    private final Class<?> serviceClass;
    private Object value;

    public CallSite(Class<?> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public Class<?> getServiceClass() {
        return serviceClass;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
