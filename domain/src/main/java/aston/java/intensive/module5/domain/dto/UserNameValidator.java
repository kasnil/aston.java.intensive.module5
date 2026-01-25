package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.validation.annotation.Validation;
import aston.java.intensive.module5.utils.validation.Validator;

@Validation
public final class UserNameValidator extends Validator<UserName> {
    public UserNameValidator() {
        addRule(UserName::value).notNull("Имя не может быть пустым").hasLengthBetween(2, 50, "Имя должно быть от 2 до 50 символов");
    }
}
