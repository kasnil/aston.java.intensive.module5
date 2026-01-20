package aston.java.intensive.module5.utils.guard;

public final class BoolGuard {
    public Boolean isTrue(Boolean value)
    {
        Ensure.any.isNotNull(value);

        if (!value) {
            throw Ensure.exceptionFactory.argumentException(ExceptionMessages.BOOLEAN_IS_FALSE);
        }

        return value;
    }

    public Boolean isFalse(Boolean value)
    {
        Ensure.any.isNotNull(value);

        if (value) {
            throw Ensure.exceptionFactory.argumentException(ExceptionMessages.BOOLEAN_IS_TRUE);
        }

        return value;
    }
}
