package aston.java.intensive.module5.utils.guard;

public final class IntegerParam extends Param<Integer> {
    public IntegerParam(Integer value) {
        super(value);
    }

    public IntegerParam isNonNegative()
    {
        return isNonNegative(null);
    }

    public IntegerParam isNonNegative(String errorMessage)
    {
        Ensure.integer.isNonNegative(this.value, errorMessage);

        return this;
    }

    public IntegerParam is(Integer expected)
    {
        return is(expected, null);
    }

    public IntegerParam is(Integer expected, String errorMessage)
    {
        Ensure.integer.is(this.value, expected, errorMessage);

        return this;
    }

    public IntegerParam isNot(Integer expected)
    {
        return isNot(expected, null);
    }

    public IntegerParam isNot(Integer expected, String errorMessage)
    {
        Ensure.integer.isNot(this.value, expected, errorMessage);

        return this;
    }
}