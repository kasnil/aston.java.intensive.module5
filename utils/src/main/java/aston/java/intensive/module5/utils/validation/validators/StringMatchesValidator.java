package aston.java.intensive.module5.utils.validation.validators;

import aston.java.intensive.module5.utils.validation.DefaultErrorMessages;
import aston.java.intensive.module5.utils.validation.Validation;
import aston.java.intensive.module5.utils.validation.ValidationFailure;

import java.util.Optional;
import java.util.regex.Pattern;

@Validation
public final class StringMatchesValidator implements PropertyValidator<String> {
    public final Pattern regexPattern;
    private final String errorMessage;
    private final NotNullValidator<String> notNullValidator;

    public StringMatchesValidator(Pattern regexPattern, String errorMessage) {
        this.regexPattern = regexPattern;
        this.errorMessage = errorMessage;
        this.notNullValidator = new NotNullValidator<>(errorMessage);
    }

    public StringMatchesValidator(String regex, String errorMessage) {
        this(Pattern.compile(regex), errorMessage);
    }

    public StringMatchesValidator(String regex) {
        this(Pattern.compile(regex), null);
    }

    @Override
    public Optional<ValidationFailure> validate(String value) {
        var notNullValidateResult = notNullValidator.validate(value);
        if (notNullValidateResult.isPresent()) {
            return notNullValidateResult;
        }

        if (!regexPattern.matcher(value).matches()) {
            String message = errorMessage == null ? String.format(DefaultErrorMessages.STRING_IS_NOT_MATCH, value, regexPattern.pattern()) : errorMessage;
            return Optional.of(new ValidationFailure(message));
        }

        return Optional.empty();
    }
}
