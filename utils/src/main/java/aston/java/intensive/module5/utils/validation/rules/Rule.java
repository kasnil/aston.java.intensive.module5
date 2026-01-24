package aston.java.intensive.module5.utils.validation.rules;

import aston.java.intensive.module5.utils.validation.ValidationFailure;
import aston.java.intensive.module5.utils.validation.ValidationResult;
import aston.java.intensive.module5.utils.validation.validators.PropertyValidator;

import java.util.HashSet;
import java.util.List;

public class Rule<T> {
    private final T value;
    private final List<PropertyValidator<T>> validators;

    public Rule(T value, List<PropertyValidator<T>> validators) {
        this.value = value;
        this.validators = List.copyOf(validators);
    }

    public ValidationResult validate() {
        var errors = new HashSet<ValidationFailure>();
        for (var validator : validators) {
            validator.validate(this.value).ifPresent(error -> errors.add(error));
        }

        return new ValidationResult(errors);
    }
}
