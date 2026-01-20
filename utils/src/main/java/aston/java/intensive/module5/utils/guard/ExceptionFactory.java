package aston.java.intensive.module5.utils.guard;

public interface ExceptionFactory {
    RuntimeException argumentNullException(String defaultMessage);
    RuntimeException argumentException(String defaultMessage);
}
