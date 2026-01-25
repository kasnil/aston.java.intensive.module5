package aston.java.intensive.module5.utils.validation.validators;

import aston.java.intensive.module5.utils.validation.DefaultErrorMessages;
import aston.java.intensive.module5.utils.validation.annotation.Validation;
import aston.java.intensive.module5.utils.validation.ValidationFailure;

import java.util.Optional;

@Validation
public final class StringLengthValidator implements PropertyValidator<String> {
    private final int length;
    private final String errorMessage;
    private final NotNullValidator<String> notNullValidator;

    public StringLengthValidator(int length, String errorMessage) {
        this.length = length;
        this.errorMessage = errorMessage;
        this.notNullValidator = new NotNullValidator<>(errorMessage);
    }

    public StringLengthValidator(int length) {
        this(length, null);
    }

    @Override
    public Optional<ValidationFailure> validate(String value) {
        var notNullValidateResult = notNullValidator.validate(value);
        if (notNullValidateResult.isPresent()) {
            return notNullValidateResult;
        }

        if (value.length() != length) {
            String message = errorMessage == null ? DefaultErrorMessages.STRING_HAS_INCORRECT_LENGTH : errorMessage;
            return Optional.of(new ValidationFailure(message));
        }

        return Optional.empty();
    }
}
