package aston.java.intensive.module5.utils.validation.validators;

import aston.java.intensive.module5.utils.validation.DefaultErrorMessages;
import aston.java.intensive.module5.utils.validation.Validation;
import aston.java.intensive.module5.utils.validation.ValidationFailure;

import java.util.Optional;

@Validation
public final class NotNullValidator<T> implements PropertyValidator<T> {
    private final String errorMessage;

    public NotNullValidator(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public NotNullValidator() {
        this(null);
    }

    @Override
    public Optional<ValidationFailure> validate(T value) {
        if (value == null) {
            String message = errorMessage == null ? DefaultErrorMessages.ANY_IS_NULL : errorMessage;
            return Optional.of(new ValidationFailure(message));
        }
        return Optional.empty();
    }
}
