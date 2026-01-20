package aston.java.intensive.module5.utils.guard;

public class DefaultExceptionFactory implements ExceptionFactory {
    public RuntimeException argumentNullException(String defaultMessage) {
        return new IllegalArgumentException(defaultMessage);
    }

    public RuntimeException argumentException(String defaultMessage) {
        return new IllegalArgumentException(defaultMessage);
    }
}
