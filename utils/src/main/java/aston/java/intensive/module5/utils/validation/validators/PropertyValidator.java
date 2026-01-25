package aston.java.intensive.module5.utils.validation.validators;

import aston.java.intensive.module5.utils.validation.ValidationFailure;

import java.util.Optional;

public interface PropertyValidator<T> {
    Optional<ValidationFailure> validate(T value);
}
