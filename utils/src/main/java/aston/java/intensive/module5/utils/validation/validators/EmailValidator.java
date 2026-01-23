package aston.java.intensive.module5.utils.validation.validators;

public final class EmailValidator extends PropertyValidator<String> {
    public static final String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public EmailValidator() {
        super(EmailValidator.class.getSimpleName());
    }

    @Override
    public boolean isValid(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return value.matches(emailRegex);
    }
}
