package aston.java.intensive.module5.utils.guard;

public final class BooleanParam extends Param<Boolean> {
    public BooleanParam(Boolean value) {
        super(value);
    }

    public BooleanParam isTrue()
    {
        Ensure.bool.isTrue(this.value);

        return this;
    }

    public BooleanParam isFalse()
    {
        Ensure.bool.isFalse(this.value);

        return this;
    }
}