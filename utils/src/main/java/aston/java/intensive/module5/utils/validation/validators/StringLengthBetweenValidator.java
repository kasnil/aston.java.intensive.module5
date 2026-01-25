package aston.java.intensive.module5.utils.validation.validators;

import aston.java.intensive.module5.utils.validation.DefaultErrorMessages;
import aston.java.intensive.module5.utils.validation.annotation.Validation;
import aston.java.intensive.module5.utils.validation.ValidationFailure;

import java.util.Optional;

@Validation
public final class StringLengthBetweenValidator implements PropertyValidator<String> {
    private final int minLength;
    private final int maxLength;
    private final String errorMessage;
    private final NotNullValidator<String> notNullValidator;

    public StringLengthBetweenValidator(int minLength, int maxLength, String errorMessage) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.errorMessage = errorMessage;
        this.notNullValidator = new NotNullValidator<>(errorMessage);
    }

    public StringLengthBetweenValidator(int minLength, int maxLength) {
        this(minLength, maxLength, null);
    }

    @Override
    public Optional<ValidationFailure> validate(String value) {
        var notNullValidateResult = notNullValidator.validate(value);
        if (notNullValidateResult.isPresent()) {
            return notNullValidateResult;
        }

        int length = value.length();
        if (length < minLength) {
            String message = errorMessage == null ? String.format(DefaultErrorMessages.STRING_HAS_TOO_SHORT_LENGTH, minLength, maxLength, length) : errorMessage;
            return Optional.of(new ValidationFailure(message));
        }
        if (length > maxLength) {
            String message = errorMessage == null ? String.format(DefaultErrorMessages.STRING_HAS_TOO_LONG_LENGTH, minLength, maxLength, length) : errorMessage;
            return Optional.of(new ValidationFailure(message));
        }

        return Optional.empty();
    }
}
