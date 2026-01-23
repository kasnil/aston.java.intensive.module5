package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.guard.Ensure;

public record Password(String value) {
    public Password {
        Ensure.that(value)
                .isNotNullOrEmpty("Пароль не может быть пустым")
                .hasLengthBetween(6, 255, "Пароль должен содержать минимум 6 символов");
    }

    public static Result<Password> of(String value) {
        try
        {
            Password password = new Password(value);
            return Result.ok(password);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }
}
