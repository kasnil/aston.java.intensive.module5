package aston.java.intensive.module5.utils.guard;

public final class AnyGuard {
    public <T> T isNotNull(T value, String errorMessage) {
        if (value == null) {
            String message = errorMessage == null ? ExceptionMessages.ANY_IS_NULL : errorMessage;
            throw Ensure.exceptionFactory.argumentNullException(message);
        }

        return value;
    }
}
