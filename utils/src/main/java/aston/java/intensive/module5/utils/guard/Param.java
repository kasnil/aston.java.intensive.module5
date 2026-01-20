package aston.java.intensive.module5.utils.guard;

public sealed class Param<T> permits StringParam, BooleanParam, CollectionParam {
    protected final T value;

    public Param(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}