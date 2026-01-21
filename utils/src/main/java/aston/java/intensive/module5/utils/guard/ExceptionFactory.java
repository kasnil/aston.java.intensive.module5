package aston.java.intensive.module5.utils.guard;

public interface ExceptionFactory {
    RuntimeException argumentNullException(String defaultMessage, Object... args);
    RuntimeException argumentException(String defaultMessage, Object... args);
}
