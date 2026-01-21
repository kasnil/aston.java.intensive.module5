package aston.java.intensive.module5.utils.guard;

public final class StringGuard {
    public String isNotNull(String value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        return value;
    }

    public String isNotNullOrEmpty(String value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.trim().length() == 0) {
            String message = errorMessage == null ? ExceptionMessages.STRING_IS_NULL_OR_EMPTY : errorMessage;
            throw Ensure.exceptionFactory.argumentNullException(message);
        }

        return value;
    }
}
