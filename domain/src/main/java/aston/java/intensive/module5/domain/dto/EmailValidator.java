package aston.java.intensive.module5.domain.dto;

import aston.java.intensive.module5.utils.validation.annotation.Validation;
import aston.java.intensive.module5.utils.validation.Validator;

@Validation
public final class EmailValidator extends Validator<Email> {
    public EmailValidator() {
        addRule(Email::value).notNull("email не может быть пустым").email("Неверный формат email");
    }
}
