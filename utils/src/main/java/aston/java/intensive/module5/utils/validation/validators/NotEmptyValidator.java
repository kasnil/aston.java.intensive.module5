package aston.java.intensive.module5.utils.validation.validators;

public final class NotEmptyValidator extends PropertyValidator<String> {
    public NotEmptyValidator() {
        super(NotEmptyValidator.class.getSimpleName());
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return false;
        }

        return !value.isEmpty();
    }
}
