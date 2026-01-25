package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.Result;

public record Password(String value) {
    public static Result<Password> of(String value) {
        var password = new Password(value);
        var validator = new PasswordValidator();
        var validateResult = validator.validate(password);
        return validateResult.isValid() ? Result.ok(password) : Result.err(validateResult.getException().get());
    }
}
