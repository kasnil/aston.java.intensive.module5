package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.Result;
import aston.java.intensive.module5.utils.guard.Ensure;

public record UserName(String value) {
    public UserName {
        value = value.trim();
        Ensure.that(value)
                .isNotNullOrEmpty("Имя не может быть пустым")
                .hasLengthBetween(2, 50, "Имя должно быть от 2 до 50 символов");
    }

    public static Result<UserName> of(String value) {
        try
        {
            UserName userName = new UserName(value);
            return Result.ok(userName);
        } catch (RuntimeException e) {
            return Result.err(e);
        }
    }
}
