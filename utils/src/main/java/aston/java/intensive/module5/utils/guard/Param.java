package aston.java.intensive.module5.utils.guard;

public sealed class Param<T> permits StringParam, BooleanParam, CollectionParam {
    protected final T value;

    public Param(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Param<T> isNotNull()
    {
        return isNotNull(null);
    }

    public Param<T> isNotNull(String errorMessage)
    {
        Ensure.any.isNotNull(this.value, errorMessage);

        return this;
    }
}