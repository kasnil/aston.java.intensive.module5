package aston.java.intensive.module5.utils.guard;

public final class IntegerGuard {
    public Integer isNonNegative(Integer value, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value < 0) {
            String message = errorMessage == null ? String.format(ExceptionMessages.INTEGER_IS_NEGATIVE, value) : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public Integer is(Integer value, Integer expected, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.compareTo(expected) != 0) {
            String message = errorMessage == null ? String.format(ExceptionMessages.COMPARABLE_IS_NOT_EQUAL, value, expected) : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }

    public Integer isNot(Integer value, Integer expected, String errorMessage)
    {
        Ensure.any.isNotNull(value, errorMessage);

        if (value.compareTo(expected) == 0) {
            String message = errorMessage == null ? String.format(ExceptionMessages.COMPARABLE_IS_EQUAL, value, expected) : errorMessage;
            throw Ensure.exceptionFactory.argumentException(message);
        }

        return value;
    }
}
