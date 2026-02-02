package aston.java.intensive.module5.utils.validation;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

public class ValidationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<ValidationFailure> errors;

    public ValidationException(String message) {
        super(message);
        this.errors = Collections.emptyList();
    }

    public ValidationException(String message, List<ValidationFailure> errors) {
        var errorMessage = (message == null || message.isEmpty())
                ? buildErrorMessage(errors)
                : message + " " + buildErrorMessage(errors);
        super(errorMessage);
        if (errors == null) {
            this.errors = Collections.emptyList();
        } else {
            this.errors = errors;
        }
    }

    public ValidationException(List<ValidationFailure> errors) {
        this(null, errors);
    }

    public List<ValidationFailure> getErrors() {
        return this.errors;
    }

    private static String buildErrorMessage(List<ValidationFailure> errors) {
        var sb = new StringBuilder("Validation failed: ");
        for(var error: errors) {
            sb.append("\n").append(error.errorMessage());
        }
        return sb.toString();
    }
}
