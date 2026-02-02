package aston.java.intensive.module5.utils;

public class ReflectionException extends RuntimeException {
    public ReflectionException(Throwable cause) {
        super(cause.getClass().getName() + ": " + cause.getMessage());
    }
}
