package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.validation.annotation.Validation;
import aston.java.intensive.module5.utils.validation.Validator;

@Validation
public final class PasswordValidator extends Validator<Password> {
    public PasswordValidator() {
        addRule(Password::value).notNull("Пароль не может быть пустым").hasLengthBetween(6, 255, "Пароль должен содержать минимум 6 символов");
    }
}
