package aston.java.intensive.module5.utils.guard;

public final class StringGuard {
    public String isNotNull(String value)
    {
        Ensure.any.isNotNull(value);

        return value;
    }

    public String isNotNullOrEmpty(String value)
    {
        Ensure.any.isNotNull(value);

        if (value.trim().length() == 0) {
            throw Ensure.exceptionFactory.argumentNullException(ExceptionMessages.STRING_IS_NULL_OR_EMPTY);
        }

        return value;
    }
}
