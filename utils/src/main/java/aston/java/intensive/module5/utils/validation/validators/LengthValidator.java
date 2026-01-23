package aston.java.intensive.module5.utils.validation.validators;

public final class LengthValidator extends PropertyValidator<String> {
    private final int length;

    public LengthValidator(int length) {
        super(LengthValidator.class.getSimpleName());
        this.length = length;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return false;
        }

        return value.length() == length;
    }
}
