package aston.java.intensive.module5.utils.validation;

import java.util.*;

public final class ValidationResult {
    private final ValidationException exception;

    public ValidationResult() {
        this.exception = null;
    }
    public ValidationResult(List<ValidationFailure> errors) {
        this.exception = errors == null || errors.isEmpty() ? null : new ValidationException(errors);
    }

    public ValidationResult(Set<ValidationFailure> errors) {
        this.exception = errors == null || errors.isEmpty() ? null : new ValidationException(List.copyOf(errors));
    }

    public boolean isValid() {
        return this.exception == null;
    }

    public Optional<ValidationException> getException() {
        return this.exception != null ? Optional.of(this.exception) : Optional.empty();
    }

    public List<ValidationFailure> getErrors() {
        return this.exception == null ? Collections.emptyList() : this.exception.getErrors();
    }

    @Override
    public String toString() {
        return toString("\n");
    }

    public String toString(String separator) {
        return String.join(separator, getErrors().stream().map(failure -> failure.errorMessage()).toList());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ValidationResult that)) return false;
        return Objects.equals(exception, that.exception);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exception);
    }
}
