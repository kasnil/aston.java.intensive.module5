package aston.java.intensive.module5.utils.guard;

import aston.java.intensive.module5.utils.ReflectUtils;

import java.lang.annotation.Annotation;

public final class AnyGuard {
    public <T> T isNotNull(T value, String errorMessage) {
        if (value == null) {
            String message = errorMessage == null ? ExceptionMessages.ANY_IS_NULL : errorMessage;
            throw Ensure.exceptionFactory.argumentNullException(message);
        }

        return value;
    }

    public <T, TAnnotation extends Annotation> T hasAnnotation(
            T value, Class<TAnnotation> annotationClass, String errorMessage)
    {
        var classOfValue = value.getClass();
        if (!ReflectUtils.hasAnnotation(value.getClass(), annotationClass)) {
            String message = errorMessage == null
                    ? String.format(ExceptionMessages.ANY_IS_NOT_HAS_ANNOTATION, classOfValue.getName(), annotationClass.getName())
                    : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public <T> T isImplementsInterface(T value, Class<?> interfaceClass, String errorMessage)
    {
        var classOfValue = value.getClass();
        if (!ReflectUtils.isImplementsInterface(value.getClass(), interfaceClass)) {
            String message = errorMessage == null
                    ? String.format(ExceptionMessages.ANY_IS_NOT_IMPLEMENTS_INTERFACE, classOfValue.getName(), interfaceClass.getName())
                    : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public <T extends Class<?>> T isImplementsInterface(T value, Class<?> interfaceClass, String errorMessage)
    {
        if (!ReflectUtils.isImplementsInterface(value, interfaceClass)) {
            String message = errorMessage == null
                    ? String.format(ExceptionMessages.ANY_IS_NOT_IMPLEMENTS_INTERFACE, value.getName(), interfaceClass.getName())
                    : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public <T extends Class<?>> T isInterface(T value, String errorMessage)
    {
        if (!value.isInterface()) {
            String message = errorMessage == null
                    ? String.format(ExceptionMessages.ANY_IS_NOT_INTERFACE, value.getName())
                    : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }
}
