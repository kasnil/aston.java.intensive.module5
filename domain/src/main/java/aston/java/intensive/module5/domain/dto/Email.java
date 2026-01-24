package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.Result;

public record Email(String value) {
    public static Result<Email> of(String value) {
        var email = new Email(value);
        var validator = new EmailValidator();
        var validateResult = validator.validate(email);
        return validateResult.isValid() ? Result.ok(email) : Result.err(validateResult.getException().get());
    }
}
