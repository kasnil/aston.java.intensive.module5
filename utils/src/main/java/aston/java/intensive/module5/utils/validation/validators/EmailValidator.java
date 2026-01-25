package aston.java.intensive.module5.utils.validation.validators;

import aston.java.intensive.module5.utils.validation.annotation.Validation;
import aston.java.intensive.module5.utils.validation.ValidationFailure;

import java.util.Optional;
import java.util.regex.Pattern;

@Validation
public final class EmailValidator implements PropertyValidator<String> {
    public static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final String errorMessage;
    private final StringMatchesValidator matchesValidator;

    public EmailValidator(String errorMessage) {
        this.errorMessage = errorMessage;
        this.matchesValidator = new StringMatchesValidator(emailPattern, errorMessage);
    }

    public EmailValidator() {
        this(null);
    }

    @Override
    public Optional<ValidationFailure> validate(String value) {
        var matchesValidateResult = matchesValidator.validate(value);
        if (matchesValidateResult.isPresent()) {
            return matchesValidateResult;
        }
        return Optional.empty();
    }
}
