package aston.java.intensive.module5.utils.guard;

public final class StringParam extends Param<String> {
    public StringParam(String value) {
        super(value);
    }

    public StringParam isNotNull()
    {
        return isNotNull(null);
    }

    public StringParam isNotNull(String errorMessage)
    {
        Ensure.string.isNotNull(this.value, errorMessage);

        return this;
    }

    public StringParam isNotNullOrEmpty()
    {
        return isNotNullOrEmpty(null);
    }

    public StringParam isNotNullOrEmpty(String errorMessage)
    {
        Ensure.string.isNotNullOrEmpty(this.value, errorMessage);

        return this;
    }
}