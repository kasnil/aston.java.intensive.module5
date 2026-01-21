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

    public String hasLength(String value, int expected, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.length() != expected) {
            String message = errorMessage == null ? String.format(ExceptionMessages.STRING_HAS_INCORRECT_LENGTH, expected, value.length()) : errorMessage;
            throw Ensure.exceptionFactory.argumentNullException(message);
        }

        return value;
    }

    public String hasLengthBetween(String value, int minLength, int maxLength, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        int length = value.length();

        if (length < minLength) {
            String message = errorMessage == null ? String.format(ExceptionMessages.STRING_HAS_TOO_SHORT_LENGTH, minLength, maxLength, length) : errorMessage;
            throw Ensure.exceptionFactory.argumentNullException(message);
        }
        if (length > maxLength) {
            String message = errorMessage == null ? String.format(ExceptionMessages.STRING_HAS_TOO_LONG_LENGTH, minLength, maxLength, length) : errorMessage;
            throw Ensure.exceptionFactory.argumentNullException(message);
        }

        return value;
    }

    public String matches(String value, String regex, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if(!value.matches(regex)) {
            String message = errorMessage == null ? String.format(ExceptionMessages.STRING_IS_NOT_MATCH, value, regex) : errorMessage;
            throw Ensure.exceptionFactory.argumentNullException(message);
        }

        return value;
    }
}
