package aston.java.intensive.module5.domain;

import aston.java.intensive.module5.utils.validation.Validation;
import aston.java.intensive.module5.utils.validation.Validator;

@Validation
public final class UserValidator extends Validator<User> {
    public UserValidator() {
        addStringRule(User::getName).notNull("Имя не может быть пустым").hasLengthBetween(2, 50, "Имя должно быть от 2 до 50 символов");
        addStringRule(User::getEmail).notNull("email не может быть пустым").email("Неверный формат email");
        addStringRule(User::getPassword).notNull("Пароль не может быть пустым").hasLengthBetween(6, 255, "Пароль должен содержать минимум 6 символов");
    }
}
