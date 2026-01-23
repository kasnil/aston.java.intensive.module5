package aston.java.intensive.module5.utils.validation.validators;

public final class NotNullValidator extends PropertyValidator<String> {
    public NotNullValidator() {
        super(NotNullValidator.class.getSimpleName());
    }

    @Override
    public boolean isValid(String value) {
        return value != null;
    }
}
