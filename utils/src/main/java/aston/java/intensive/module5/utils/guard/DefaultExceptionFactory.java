package aston.java.intensive.module5.utils.guard;

public class DefaultExceptionFactory implements ExceptionFactory {
    public RuntimeException argumentNullException(String defaultMessage, Object... args) {
        return new IllegalArgumentException(String.format(defaultMessage, args));
    }

    public RuntimeException argumentException(String defaultMessage, Object... args) {
        return new IllegalArgumentException(String.format(defaultMessage, args));
    }
}
