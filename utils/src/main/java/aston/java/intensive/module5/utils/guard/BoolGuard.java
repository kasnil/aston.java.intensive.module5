package aston.java.intensive.module5.utils.guard;

public final class BoolGuard {
    public Boolean isTrue(Boolean value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (!value) {
            String message = errorMessage == null ? ExceptionMessages.BOOLEAN_IS_FALSE : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public Boolean isFalse(Boolean value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value) {
            String message = errorMessage == null ? ExceptionMessages.BOOLEAN_IS_TRUE : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }
}
