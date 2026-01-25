package aston.java.intensive.module5.utils.validation.rules;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.validation.validators.*;

import java.util.List;
import java.util.function.Function;

public class StringRuleBuilder<T> implements RuleBuilder<T, String> {
    private final Function<T, String> field;
    private final List<PropertyValidator<String>> validators = ListsUtils.newArrayList();

    public StringRuleBuilder(Function<T, String> field) {
        this.field = field;
    }

    public StringRuleBuilder<T> notNull() {
        return notNull(null);
    }

    public StringRuleBuilder<T> notNull(String errorMessage) {
        validators.add(new NotNullValidator<>(errorMessage));
        return this;
    }

    public StringRuleBuilder<T> email() {
        return email(null);
    }

    public StringRuleBuilder<T> email(String errorMessage) {
        validators.add(new EmailValidator(errorMessage));
        return this;
    }

    public StringRuleBuilder<T> hasLength(int expected) {
        return hasLength(expected, null);
    }

    public StringRuleBuilder<T> hasLength(int expected, String errorMessage) {
        validators.add(new StringLengthValidator(expected, errorMessage));
        return this;
    }

    public StringRuleBuilder<T> hasLengthBetween(int minLength, int maxLength) {
        return hasLengthBetween(minLength, maxLength, null);
    }

    public StringRuleBuilder<T> hasLengthBetween(int minLength, int maxLength, String errorMessage) {
        validators.add(new StringLengthBetweenValidator(minLength, maxLength, errorMessage));
        return this;
    }

    public StringRuleBuilder<T> matches(String regex) {
        return matches(regex, null);
    }

    public StringRuleBuilder<T> matches(String regex, String errorMessage) {
        validators.add(new StringMatchesValidator(regex, errorMessage));
        return this;
    }

    @Override
    public Rule<String> build(T inst) {
        return new Rule<>(this.field.apply(inst), this.validators);
    }
}
