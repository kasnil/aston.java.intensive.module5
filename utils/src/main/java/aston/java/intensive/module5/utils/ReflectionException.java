package aston.java.intensive.module5.utils;

public class ReflectionException extends RuntimeException {
    private Throwable cause;

    public ReflectionException(Throwable cause) {
        super(cause.getClass().getName() + ": " + cause.getMessage());
        this.cause = cause;
    }
}
