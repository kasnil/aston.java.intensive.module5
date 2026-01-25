package aston.java.intensive.module5.utils.validation.rules;

public interface RuleBuilder<T, TProperty> {
    Rule<TProperty> build(T inst);
}
