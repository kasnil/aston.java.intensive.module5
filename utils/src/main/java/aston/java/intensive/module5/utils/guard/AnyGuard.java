package aston.java.intensive.module5.utils.guard;

public final class AnyGuard {
    public <T> T isNotNull(T value) {
        if (value == null) {
            throw Ensure.exceptionFactory.argumentNullException(ExceptionMessages.ANY_IS_NULL);
        }

        return value;
    }
}
