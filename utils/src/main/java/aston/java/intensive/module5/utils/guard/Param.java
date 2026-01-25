package aston.java.intensive.module5.utils.guard;

import java.lang.annotation.Annotation;

public sealed class Param<T> permits StringParam, BooleanParam, IntegerParam, CollectionParam, ClassParam {
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

    public <TAnnotation extends Annotation> Param<T> hasAnnotation(
            Class<TAnnotation> annotationClass)
    {
        return hasAnnotation(annotationClass, null);
    }

    public <TAnnotation extends Annotation> Param<T> hasAnnotation(
            Class<TAnnotation> annotationClass, String errorMessage)
    {
        Ensure.any.hasAnnotation(this.value, annotationClass, errorMessage);

        return this;
    }

    public Param<T> isImplementsInterface(
            Class<?> interfaceClass)
    {
        return isImplementsInterface(interfaceClass, null);
    }

    public Param<T> isImplementsInterface(
            Class<?> interfaceClass, String errorMessage)
    {
        Ensure.any.isImplementsInterface(this.value, interfaceClass, errorMessage);

        return this;
    }
}