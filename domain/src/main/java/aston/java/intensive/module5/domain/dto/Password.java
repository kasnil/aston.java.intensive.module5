package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.guard.Ensure;

public record Password(String value) {
    public Password {
        Ensure.that(value)
                .isNotNullOrEmpty("Пароль не может быть пустым")
                .hasLengthBetween(6, 255, "Пароль должен содержать минимум 6 символов");
    }
}
