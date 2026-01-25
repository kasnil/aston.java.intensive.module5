package aston.java.intensive.module5.utils.validation;

import aston.java.intensive.module5.utils.ListsUtils;
import aston.java.intensive.module5.utils.guard.Ensure;
import aston.java.intensive.module5.utils.validation.rules.RuleBuilder;
import aston.java.intensive.module5.utils.validation.rules.StringRuleBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

public abstract class Validator<T> {
    private final List<RuleBuilder> rules = ListsUtils.newArrayList();

    protected StringRuleBuilder addRule(Function<T, String> field) {
        var ruleBuilder = new StringRuleBuilder<>(field);
        rules.add(ruleBuilder);
        return ruleBuilder;
    }

    public ValidationResult validate(T inst) {
        Ensure.that(inst).isNotNull();

        var result = new HashSet<ValidationFailure>();
        for (var rule : this.rules) {
            var validateResult = rule.build(inst).validate();
            if (!validateResult.isValid()) {
                result.addAll(validateResult.getErrors());
            }
        }
        return new ValidationResult(result);
    }

    public void validateOrElseThrow(T inst) {
        var exception = validate(inst).getException();
        validate(inst).getException().ifPresent(e -> {
            throw e;
        });
    }
}
