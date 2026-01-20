package aston.java.intensive.module5.utils.guard;

public final class StringParam extends Param<String> {
    public StringParam(String value) {
        super(value);
    }

    public StringParam isNotNull()
    {
        Ensure.string.isNotNull(this.value);

        return this;
    }

    public StringParam isNotNullOrEmpty()
    {
        Ensure.string.isNotNullOrEmpty(this.value);

        return this;
    }
}