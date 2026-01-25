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
}