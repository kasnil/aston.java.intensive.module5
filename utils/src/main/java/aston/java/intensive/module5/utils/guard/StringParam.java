package aston.java.intensive.module5.utils.guard;

public final class StringParam extends Param<String> {
    public StringParam(String value) {
        super(value);
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

    public StringParam hasLength(int expected) {
        return hasLength(expected, null);
    }

    public StringParam hasLength(int expected, String errorMessage)
    {
        Ensure.string.hasLength(this.value, expected, errorMessage);

        return this;
    }

    public StringParam hasLengthBetween(int minLength, int maxLength) {
        return hasLengthBetween(minLength, maxLength, null);
    }

    public StringParam hasLengthBetween(int minLength, int maxLength, String errorMessage)
    {
        Ensure.string.hasLengthBetween(this.value, minLength, maxLength, errorMessage);

        return this;
    }

    public StringParam matches(String regex) {
        return matches(regex, null);
    }

    public StringParam matches(String regex, String errorMessage)
    {
        Ensure.string.matches(this.value, regex, errorMessage);

        return this;
    }
}