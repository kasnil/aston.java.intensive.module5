package aston.java.intensive.module5.utils.menu;

public class NotSupportedException extends RuntimeException {
    public NotSupportedException(String message) {
        super(message);
    }

    public NotSupportedException(Throwable cause) {
        super(cause.getClass().getName() + ": " + cause.getMessage());
    }
}
