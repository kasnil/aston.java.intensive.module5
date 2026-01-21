package aston.java.intensive.module5.utils.guard;

public final class BooleanParam extends Param<Boolean> {
    public BooleanParam(Boolean value) {
        super(value);
    }

    public BooleanParam isTrue()
    {
        return isTrue(null);
    }

    public BooleanParam isTrue(String errorMessage)
    {
        Ensure.bool.isTrue(this.value, errorMessage);

        return this;
    }

    public BooleanParam isFalse()
    {
        return isFalse(null);
    }

    public BooleanParam isFalse(String errorMessage)
    {
        Ensure.bool.isFalse(this.value, errorMessage);

        return this;
    }
}