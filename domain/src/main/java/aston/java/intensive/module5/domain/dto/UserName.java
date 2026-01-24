package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.Result;

public record UserName(String value) {
    public static Result<UserName> of(String value) {
        var userName = new UserName(value);
        var validator = new UserNameValidator();
        var validateResult = validator.validate(userName);
        return validateResult.isValid() ? Result.ok(userName) : Result.err(validateResult.getException().get());
    }
}
