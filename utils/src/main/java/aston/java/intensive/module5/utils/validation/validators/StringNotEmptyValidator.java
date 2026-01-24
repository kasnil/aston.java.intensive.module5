package aston.java.intensive.module5.utils.validation.validators;

import aston.java.intensive.module5.utils.validation.DefaultErrorMessages;
import aston.java.intensive.module5.utils.validation.Validation;
import aston.java.intensive.module5.utils.validation.ValidationFailure;

import java.util.Optional;

@Validation
public final class StringNotEmptyValidator implements PropertyValidator<String> {
    private final String errorMessage;
    private final NotNullValidator<String> notNullValidator;

    public StringNotEmptyValidator(String errorMessage) {
        this.errorMessage = errorMessage;
        this.notNullValidator = new NotNullValidator<>(errorMessage);
    }

    public StringNotEmptyValidator() {
        this(null);
    }

    @Override
    public Optional<ValidationFailure> validate(String value) {
        var notNullValidateResult = notNullValidator.validate(value);
        if (notNullValidateResult.isPresent()) {
            return notNullValidateResult;
        }

        if (value.isEmpty()) {
            String message = errorMessage == null ? DefaultErrorMessages.STRING_IS_EMPTY : errorMessage;
            return Optional.of(new ValidationFailure(message));
        }

        return Optional.empty();
    }
}
