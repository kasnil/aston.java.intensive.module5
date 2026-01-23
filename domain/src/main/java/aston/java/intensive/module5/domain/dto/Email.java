package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.guard.Ensure;

public record Email(String value) {
    public Email {
        Ensure.that(value)
                .isNotNullOrEmpty("email не может быть пустым")
                .matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", "Неверный формат email");
    }
}
