package aston.java.intensive.module5.utils.guard;

import java.lang.annotation.Annotation;

public final class ClassParam<T extends Class<?>> extends Param<T> {
    public ClassParam(T value) {
        super(value);
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

    public ClassParam<T> isImplementsInterface(
            Class<?> interfaceClass)
    {
        return isImplementsInterface(interfaceClass, null);
    }

    public ClassParam<T> isImplementsInterface(
            Class<?> interfaceClass, String errorMessage)
    {
        Ensure.any.isImplementsInterface(this.value, interfaceClass, errorMessage);

        return this;
    }

    public ClassParam<T> isInterface()
    {
        return isInterface(null);
    }

    public ClassParam<T> isInterface(String errorMessage)
    {
        Ensure.any.isInterface(this.value, errorMessage);

        return this;
    }
}