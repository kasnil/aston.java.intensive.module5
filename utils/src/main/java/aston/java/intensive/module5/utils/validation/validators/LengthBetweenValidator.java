package aston.java.intensive.module5.utils.validation.validators;

public final class LengthBetweenValidator extends PropertyValidator<String> {
    private final int minLength;
    private final int maxLength;

    public LengthBetweenValidator(int minLength, int maxLength) {
        super(LengthBetweenValidator.class.getSimpleName());
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return false;
        }

        int length = value.length();
        return length >= this.minLength && length <= this.maxLength;
    }
}
